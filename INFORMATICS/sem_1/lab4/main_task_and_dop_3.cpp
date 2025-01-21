#include <iostream>
#include <fstream>
#include <sstream>
#include <regex>
#include <map>
#include <vector>
#include <string>
#include <any>
#include <stack>

using namespace std;

class YamlParser {
private:
    map<string, any> parsedYaml;
    map<string, any> anchors;
    map<string, any>* currentDict;
    map<string, any>* currentAnchor;
    vector<string> currentList;
    stack<pair<map<string, any>*, int>> dictStack;
    stack<pair<map<string, any>*, int>> anchorStack;

    bool isStringArray;
    bool isString;
    bool isAnchor;
    int lastAnchorEnclosure;
    int lastEnclosure;

    int getEnclosureLevel(const string& line) {
        return line.find_first_not_of(' ') == string::npos ? line.length() : line.find_first_not_of(' ');
    }

    void parseLine(const string& line) {
        if (line.empty() || line[0] == '#') return;

        string currentLine = line;
        if (currentLine.find("- ") == 0 && currentLine.find(":") != string::npos) {
            currentLine = currentLine.substr(2);
        }

        int enclosure = getEnclosureLevel(currentLine);

        if (enclosure < lastEnclosure) {
            if (isStringArray) {
                isStringArray = false;
            }
        }

        if ((currentLine.find("- ") == 0 && currentLine.find(":") == string::npos) || isStringArray) {
            currentList.push_back(currentLine.substr(2));
            return;
        }

        if (!currentList.empty()) {
            if (isAnchor) {
                (*currentAnchor)[currentLine] = currentList;
            }
            else {
                (*currentDict)[currentLine] = currentList;
            }
            currentList.clear();
        }

        if (currentLine.find(":") != string::npos) {
            string key = currentLine.substr(0, currentLine.find(":"));
            string value = currentLine.substr(currentLine.find(":") + 1);

            if (value.find("&") != string::npos) {
                isAnchor = true;
            }

            if (isAnchor) {
                if (value.find("&") == 0) {
                    key = value.substr(1);
                    value = "";
                }

                if (enclosure > lastAnchorEnclosure) {
                    anchorStack.push({ currentAnchor, enclosure });
                }
                else if (enclosure < lastAnchorEnclosure) {
                    while (enclosure != anchorStack.top().second) {
                        anchorStack.pop();
                    }
                    currentAnchor = anchorStack.top().first;
                }

                if (!value.empty()) {
                    if (value == "|" || value == ">") {
                        isStringArray = true;
                        (*currentAnchor)[key] = map<string, any>();
                        map<string, any>& currentAnchorMap = any_cast<map<string, any>&>((*currentAnchor)[key]);
                        currentAnchor = &currentAnchorMap;
                        if (value == ">") {
                            isString = true;
                        }
                    }
                    else {
                        (*currentAnchor)[key] = value;
                    }
                }
                else {
                    (*currentAnchor)[key] = map<string, any>();
                    map<string, any>& currentAnchorMap = any_cast<map<string, any>&>((*currentAnchor)[key]);
                    currentAnchor = &currentAnchorMap;
                }
                lastAnchorEnclosure = enclosure;
            }
            else {
                if (enclosure > lastEnclosure) {
                    dictStack.push({ currentDict, enclosure });
                }
                else if (enclosure < lastEnclosure) {
                    while (enclosure != dictStack.top().second) {
                        dictStack.pop();
                    }
                    currentDict = dictStack.top().first;
                }

                lastEnclosure = enclosure;

                if (!value.empty()) {
                    (*currentDict)[key] = value;
                }
                else {
                    (*currentDict)[key] = map<string, any>();
                    map<string, any>& currentDictMap = any_cast<map<string, any>&>((*currentDict)[key]);
                    currentDict = &currentDictMap;
                }
            }
        }
    }

public:
    YamlParser() : currentDict(&parsedYaml), currentAnchor(&anchors), isStringArray(false), isString(false), isAnchor(false), lastAnchorEnclosure(-1), lastEnclosure(-1) {}

    map<string, any> parse(ifstream& file) {
        string line;
        while (getline(file, line)) {
            parseLine(line);
        }
        return parsedYaml;
    }
};

class JsonConverter {
private:
    string removeWhitespace(const string& str) {
        string result = str;
        result.erase(remove(result.begin(), result.end(), ' '), result.end());
        return result;
    }

    string convertDictionaryToJson(map<string, any>* dictionary, int currentDepth = 1) {
        string result = "";
        for (auto& entry : *dictionary) {
            string key = "\"" + removeWhitespace(entry.first) + "\"";
            string value;

            if (entry.second.type() == typeid(map<string, any>)) {
                value = convertDictionaryToJson(any_cast<map<string, any>>(&entry.second), currentDepth + 1);
                value = "{" + value + "\n" + string(currentDepth * 4, ' ') + "}";
            }
            else if (entry.second.type() == typeid(vector<string>)) {
                value = "[";
                vector<string> list = any_cast<vector<string>>(entry.second);
                for (size_t i = 0; i < list.size(); ++i) {
                    value += "\n" + string((currentDepth + 1) * 4, ' ') + "\"" + list[i] + "\"";
                    if (i != list.size() - 1) {
                        value += ",";
                    }
                }
                value += "\n" + string(currentDepth * 4, ' ') + "]";
            }
            else {
                value = "\"" + any_cast<string>(entry.second) + "\"";
            }

            result += "\n" + string(currentDepth * 4, ' ') + key + ": " + value + ",";
        }

        if (result.back() == ',') {
            result = result.substr(0, result.length() - 1);
        }
        return result;
    }

public:
    void convertYamlToJson(map<string, any>& yamlDict) {
        ofstream jsonFile("output.json");
        jsonFile << "{";
        jsonFile << convertDictionaryToJson(&yamlDict, 1);
        jsonFile << "\n}";
        jsonFile.close();
    }
};

class FileUtils {
public:
    static bool isYamlFile(const string& filename) {
        size_t dotPos = filename.rfind('.');
        if (dotPos == string::npos) {
            return false;
        }
        string extension = filename.substr(dotPos + 1);
        return extension == "yaml" || extension == "yml";
    }

    static void readFile(const string& path, YamlParser& parser) {
        if (!isYamlFile(path)) {
            cerr << "The file is not a YAML file!" << endl;
            return;
        }

        ifstream file(path);
        if (!file.is_open()) {
            cerr << "Failed to open file!" << endl;
            return;
        }

        map<string, any> yamlDict = parser.parse(file);
        file.close();

        JsonConverter converter;
        converter.convertYamlToJson(yamlDict);
    }
};

int main() {
    YamlParser parser;
    FileUtils::readFile("schedule.yaml", parser);
    return 0;
}

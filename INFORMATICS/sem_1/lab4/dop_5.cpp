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

        regex keyValueRegex(R"(^\s*(\S+)\s*:\s*(.*))");
        smatch match;
        if (regex_search(currentLine, match, keyValueRegex)) {
            string key = match[1];
            string value = match[2];

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

class WmlConverter {
private:
    string traverseDict(map<string, any>& dictionary, string wmlString = "<?xml version='1.0'?>\n<!DOCTYPE wml PUBLIC '-//WAPFORUM//DTD WML 1.1//EN' 'http://www.wapforum.org/DTD/wml_1.1.xml'>\n<wml>\n",
        vector<string> openedKeys = {}, int currentDepth = 1) {
        for (auto& entry : dictionary) {
            string key = entry.first;
            wmlString += string(currentDepth * 4, ' ') + "<card id=\"" + key + "\">\n";

            if (entry.second.type() == typeid(map<string, any>)) {
                wmlString = traverseDict(any_cast<map<string, any>&>(entry.second), wmlString, openedKeys, currentDepth + 1);
            }
            else if (entry.second.type() == typeid(vector<string>)) {
                vector<string> list = any_cast<vector<string>>(entry.second);
                for (auto& elem : list) {
                    wmlString += string((currentDepth + 1) * 4, ' ') + "<p>" + elem + "</p>\n";
                }
            }
            else {
                wmlString += string((currentDepth + 1) * 4, ' ') + "<p>" + any_cast<string>(entry.second) + "</p>\n";
            }

            wmlString += string(currentDepth * 4, ' ') + "</card>\n";
        }
        return wmlString;
    }


public:
    string convertYamlToWml(map<string, any>& yamlDict) {
        return traverseDict(yamlDict) + "</wml>\n";
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

        WmlConverter converter;
        ofstream wmlFile("output.wml");
        wmlFile << converter.convertYamlToWml(yamlDict);
        wmlFile.close();
    }
};

int main() {
    YamlParser parser;
    FileUtils::readFile("schedule.yaml", parser);
    return 0;
}

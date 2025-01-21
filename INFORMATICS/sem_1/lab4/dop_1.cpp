#include <iostream>
#include <fstream>
#include <yaml-cpp/yaml.h>
#include <nlohmann/json.hpp>

using json = nlohmann::json;
using namespace std;

class YamlToJsonConverter {
public:
    static void convertYamlToJson(const string& inputFilePath, const string& outputFilePath) {
        try {
            YAML::Node yamlNode = YAML::LoadFile(inputFilePath);
            json jsonObject = yamlToJson(yamlNode);

            ofstream outFile(outputFilePath);
            if (!outFile.is_open()) {
                cerr << "Failed to open output file: " << outputFilePath << endl;
                return;
            }

            outFile << jsonObject.dump(4);
            outFile.close();
            cout << "Conversion successful. JSON saved to " << outputFilePath << endl;
        }
        catch (const YAML::Exception& e) {
            cerr << "Error parsing YAML: " << e.what() << endl;
        }
        catch (const exception& e) {
            cerr << "Error: " << e.what() << endl;
        }
    }

private:
    static json yamlToJson(const YAML::Node& node) {
        if (node.IsScalar()) {
            return node.as<string>();
        }
        else if (node.IsSequence()) {
            json array = json::array();
            for (const auto& item : node) {
                array.push_back(yamlToJson(item));
            }
            return array;
        }
        else if (node.IsMap()) {
            json obj = json::object();
            for (const auto& kv : node) {
                obj[kv.first.as<string>()] = yamlToJson(kv.second);
            }
            return obj;
        }
        return nullptr;
    }
};

int main() {
    const string inputYamlFile = "schedule.yaml";
    const string outputJsonFile = "output.json";

    YamlToJsonConverter::convertYamlToJson(inputYamlFile, outputJsonFile);
    return 0;
}

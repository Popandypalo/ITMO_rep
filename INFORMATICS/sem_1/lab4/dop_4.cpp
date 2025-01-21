#include <iostream>
#include <chrono>
#include <string>
#include <vector>
#include <cstdlib>

using namespace std;
using namespace chrono;

void runMainTaskAndAdditionalTask3() {
    system("./main_task_and_dop_3");
}

void runAdditionalTask1() {
    system("./dop_1");
}

void runAdditionalTask2() {
    system("./dop_2"); 
}

int main() {
    vector<string> tasksList = {"main_task_and_dop_3", "dop_1", "dop_2"};
    
    for (const string& taskName : tasksList) {
        cout << taskName << ": ";
        
        auto start = high_resolution_clock::now();
        
        if (taskName == "MainTaskAndAdditionalTask3") {
            runMainTaskAndAdditionalTask3();
        } else if (taskName == "AdditionalTask1") {
            runAdditionalTask1();
        } else if (taskName == "AdditionalTask2") {
            runAdditionalTask2();
        }
        
        auto end = high_resolution_clock::now();
        auto duration = duration_cast<microseconds>(end - start);
        
        cout << duration.count() / 1000.0 << " ms" << endl;
    }

    return 0;
}

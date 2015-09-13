#include <iostream>

bool isPentagonal(int n, int* pent){
    if (n % 2 == 1) return false;
    int currentTest = 1;
    int currentTestValue = 0;
    while (currentTestValue < n){
        currentTestValue = (3*currentTest*currentTest-currentTest)/2;
        currentTest++;
        if (currentTestValue == n) {
            return true;
        }
    }
    return false;
}

int main() {
    int* pent = new int[5000];
    
    for (int i = 1; i < 5000; i++) {
        pent[i] = (3*i*i-i)/2;
    }
    
    for (int i = 1; i < 5000; i++) {
        for (int j = i; j < 5000; j++) {
            if (isPentagonal(pent[i]+pent[j], pent) && isPentagonal(pent[j]-pent[i], pent)) {
                std::cout << "P of " << i << " is " << pent[i] << " and P of " << j << " is " << pent[j] << std::endl;
                delete [] pent;
                return 0;
            }
        }
    }
    return 0;
}

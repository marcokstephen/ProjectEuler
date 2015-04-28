#include <iostream>

using namespace std;

int main(){
    const int AMOUNT = 200;
    int numSolutions = 0;
    int amt = 0;
    for (int one = 0; one <= AMOUNT; one++){
        int oneTempAmt = amt;
        amt += one;
        if (amt > AMOUNT) break;
        for (int two = 0; two <= AMOUNT; two+=2){
            int twoTempAmt = amt;
            amt += two;
            if (amt > AMOUNT) break;
            for (int five = 0; five <= AMOUNT; five+=5){
                int fiveTempAmt = amt;
                amt += five;
                if (amt > AMOUNT) break;
                for (int ten = 0; ten <= AMOUNT; ten+=10){
                    int tenTempAmt = amt;
                    amt+= ten;
                    if (amt > AMOUNT) break;
                    for (int twenty = 0; twenty <= AMOUNT; twenty+=20){
                        int twentyTempAmt = amt;
                        amt += twenty;
                        if (amt > AMOUNT) break;
                        for (int fifty = 0; fifty <= AMOUNT; fifty+=50){
                            int fiftyTempAmt = amt;
                            amt += fifty;
                            if (amt > AMOUNT) break;
                            for (int hun = 0; hun <= AMOUNT; hun+=100){
                                int hunTempAmt = amt;
                                amt += hun;
                                if (amt > AMOUNT) break;
                                for (int twoHun = 0; twoHun <= AMOUNT; twoHun+=200){
                                    amt += twoHun;
                                    if (amt > AMOUNT) break;
                                    if (amt == AMOUNT) numSolutions++;
                                }
                                amt = hunTempAmt;
                            }
                            amt = fiftyTempAmt;
                        }
                        amt = twentyTempAmt;
                    }
                    amt = tenTempAmt;
                }
                amt = fiveTempAmt;
            }
            amt = twoTempAmt;
        }
        amt = oneTempAmt;
    }
    cout << "Number of solutions: " << numSolutions << endl;
}

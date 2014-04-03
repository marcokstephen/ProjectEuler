#include <stdio.h>

const char num[] = "731671765313306249192251196744265747423553491949349698352031277450632623957
83180169848018694788518438586156078911294949545950173795833195285320880551112540698747158523863
05071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803
08135336276614282806444486645238749303589072962904915604407723907138105158593079608667017242712
18839987979087922749219016997208880937766572733300105336788122023542180975125454059475224352584
90771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474821663704844031998900088952434506585412275886
66881164271714799244429282308634656748139191231628245861786645835912456652947654568284891288314
26076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879
92724428490918884580156166097919133875499200524063689912560717606058861164671094050775410022569
8315520005593572972571636269561882670428252483600823257530420752963450";

int main(void){
   int max = 0;
   
   for (int i = 0; i <= 995; i++){
      char char1 = num[i] - 48;
      char char2 = num[i+1] - 48;
      char char3 = num[i+2] - 48;
      char char4 = num[i+3] - 48;
      char char5 = num[i+4] - 48;
      
      if (char1*char2*char3*char4*char5 > max){
         max = char1*char2*char3*char4*char5;
      }
   }
   printf("%d",max);
}

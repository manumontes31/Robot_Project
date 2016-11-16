//
// Created by fanny on 13/11/2016.
//

//#include "recoVocal.h"
#include "dtw.h"
#include "WavToMfcc.h"
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <limits>




using namespace std;

char* recoVocal(char* genre, char* filename){

    char tabMot[] = {'arretetoi', 'atterrissage', 'avance', 'decollage', 'droite', 'etatdurgence', 'faisunflip', 'gauche', 'plusbas', 'plushaut', 'recule', 'tournedroite', 'tournegauche'};


    float* hypothese = NULL;
    float  resDTW = NULL;

    /*      Cf WavToMfcc.h      */
    wavfile* w;
    FILE** f;
    char* mfcName;
    float** bufferMotCherche;
    int* tailleBufferMC;

    wavRead(f, filename, w);
    nameWavToMfc(filename, mfcName);
    //removeSilence(int16_t * x, int Nx, int16_t ** xFiltered, int * newLength, float threshold);
    /*  Parametrisation du mot cherché ???  */
    //computeMFCC(bufferMotCherche, tailleBufferMC, int16_t *x, int Nx, w->frequency, 512, 256, 13, 26);


    /*  Initialisation du tableau des différents mots (est ce un homme ou une femme?)   */
    /*   if (genre == "Homme"){
           tabHypo =new string ['V01', 'V02', 'V03'];
       }
       else if (genre == "Femme"){
           tabHypo = new string ['V01', 'V02'];
       }*/

    float min;

    int * matriceconfu;
    int tauxreco;
    char nomfichier;
    const char *indice;

    //  for(int i=0; i<tabHypo->length(); i++){

    /*  Initialisation à zero de la matrice confusion (2dimensions??)   */
    /*for (int j=0; j<size_t(tabHypo); j++){
        matriceconfu[j]=0;
    }*/
    //locuteur = tabHypo[i];

    int truc_mfcc;
    min=numeric_limits<float>::infinity();
    for (int j=0; j<size_t(tabMot); j++){

        nomfichier = ('../res/raw/Son_enregistre/FA01_'+tabMot[j]+'.wav');
        //hypothese= parametrisation(nomfichier);

        resDTW = dtw(sizeof(hypothese), *tailleBufferMC, truc_mfcc, hypothese, *bufferMotCherche);

        /*  Chercher le mot qui a la distance la plus petite avec notre enregistrement  */
        if(resDTW<min){
            min = resDTW;
            indice = (char *) tabMot[j];
        }
        //matriceconfu[j] = 1;

    }

    /* On renvoie le mot à la distance la plus petite   */
    cout << min << endl;
    return (char *) (indice);

    //  }

}

int main(){
    char * mot;
    mot = recoVocal("Homme", "../res/raw/Son_enregistre/FA01_avance.wav");
    cout << "Le mot trouvé est : "<< mot << endl;

    return(0);
}


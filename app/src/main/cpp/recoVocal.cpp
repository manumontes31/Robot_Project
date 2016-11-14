//
// Created by fanny on 13/11/2016.
//

//#include "recoVocal.h"
#include "dtw.h"
#include "WavToMfcc.h"
#include <stdio.h>
#include <stdlib.h>
#include <iostream>


float *parametrisation(std::string basic_string);

using namespace std;

char* recoVocal(char* genre, char* filename){

    std::string tabMot[] = {"arretetoi", "atterrissage", "avance", "decollage", "droite", "etatdurgence", "faisunflip", "gauche", "plusbas", "plushaut", "recule", "tournedroite", "tournegauche"};

    string *tabHypo = NULL;

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
    //computeMFCC(bufferMotCherche, tailleBufferMC, int16_t *x, int Nx, w->frequency, int sample_length, int sample_step, int dim_mfcc, int num_filter);


    /*  Initialisation du tableau des différents mots (est ce un homme ou une femme?)   */
    /*   if (genre == "Homme"){
           tabHypo =new string ['V01', 'V02', 'V03'];
       }
       else if (genre == "Femme"){
           tabHypo = new string ['V01', 'V02'];
       }*/

    int min,i;

    int * matriceconfu;
    int tauxreco;
    string locuteur, nomfichier;
    const char *indice;

    //  for(int i=0; i<tabHypo->length(); i++){

    /*  Initialisation à zero de la matrice confusion (2dimensions??)   */
    for (int j=0; j<tabHypo->length(); j++){
        matriceconfu[j]=0;
    }
    locuteur=tabHypo[i];

    int truc_mfcc;
    for (int j=0; j<tabMot->length(); j++){
        //min =  std::numeric_limits<int>::max(); //Infini
        nomfichier = ("chemin/"+locuteur+"/"+tabMot[j]+".wav");     //surrement à modifier
        hypothese= parametrisation(nomfichier);
        resDTW = dtw(sizeof(hypothese), *tailleBufferMC, truc_mfcc, hypothese, *bufferMotCherche);

        if(resDTW<min){     /* Il est ou le petit d?    */
            min = resDTW;
            indice = (char *) tabMot[j].c_str();
        }
        matriceconfu[j] = 1;

    }

    /*      Taux de reconnaissance ??? garder l'incide mini??     */

    return (char *) (indice);

    //  }

}

float* parametrisation(string basic_string) {
    return NULL;
}
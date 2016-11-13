//
// Created by fanny on 13/11/2016.
//

//#include "recoVocal.h"
#include "dtw.h"
#include <stdio.h>
#include <stdlib.h
#include <iostream>


using namespace std;

string recoVocal(string genre, string filename){

    string *tabMot = ['arretetoi', 'atterrissage', 'avance', 'decollage', 'droite', 'etatdurgence', 'faisunflip', 'gauche', 'plusbas', 'plushaut', 'recule', 'tournedroite', 'tournegauche'];

    //string *tabHypo = null;

    float* hypothese = null;
    float* tabMotCherche = null;
    float  resDTW = null;

    /*  Initialisation du tableau des différents mots (est ce un homme ou une femme?)   */
    /*   if (genre == "Homme"){
           tabHypo =new string ['V01', 'V02', 'V03'];
       }
       else if (genre == "Femme"){
           tabHypo = new string ['V01', 'V02'];
       }*/


    /*  Parametrisation du mot cherché  */
    tabMotCherche = parametrisation(filename);

    int min;

    int * matriceconfu;
    int tauxreco;
    string locuteur, nomfichier, indice;

    //  for(int i=0; i<tabHypo->length(); i++){

    /*  Initialisation à zero de la matrice confusion (2dimensions??)   */
    for (int j=0; j<tabHypo->length(); j++){
        matriceconfu[j]=0;
    }
    locuteur=tabHypo[i];

    for (int j=0; j<tabMot->length(); j++){
        min=  std::numeric_limits<int>::max(); //Infini
        nomfichier = ("chemin/"+locuteur+"/"+tabMot[j]+".wav");     //surrement à modifier
        hypothese=new float(parametrisation(nomfichier));
        resDTW = dtw(hypothese.lenght(), tabMotCherche.lenght(), truc_mfcc, hypothese, tabMotCherche);

        if(resDTW<min){     /* Il est ou le petit d?    */
            min = resDTW;
            indice = tabMot[j];
        }
        matriceconfu[j] = 1;

    }

    /*      Taux de reconnaissance ??? garder l'incide mini??     */

    return (indice);

    //  }

}
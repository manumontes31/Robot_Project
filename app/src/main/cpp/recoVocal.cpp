
//
// Created by fanny on 13/11/2016.
//

//#include "recoVocal.h"
#include "dtw.h"
#include "WavToMfcc.h"
#include <stdlib.h>
#include <limits>
#include <jni.h>

using namespace std;

extern"C"

jstring
Java_android_test_robot_Micro_recoVocal(JNIEnv *env, jobject , string filename){

    string  tabMot[] = {"arretetoi", "atterrissage", "avance", "decollage", "droite", "etatdurgence", "faisunflip", "gauche", "plusbas", "plushaut", "recule", "tournedroite", "tournegauche"};

//	char * tabMot;

    float  resDTW = NULL;

    /*      Parametrisation filename      */
    wavfile w;
    FILE* f = NULL;
    float* bufferMotCherche = NULL;
    int tailleBufferMC = 0;
    int16_t *data;
    int sizeData;

    char* p = new char[filename.length()+1];
    strcpy( p, filename.c_str());

    wavRead(&f, p, &w);

    sizeData= (w.bytes_in_data / sizeof(int16_t));
    data = new int16_t[sizeData];

    if (fread(&data[0], sizeof(int16_t), sizeData, f) <1 ){
        fprintf(stderr, "Can't read input wav data %d\n", p);
        exit(1);
    }
    computeMFCC(&bufferMotCherche, &tailleBufferMC, data, sizeData, w.frequency, 512, 256, 13, 26);


    /*  Initialisation du tableau des différents mots (est ce un homme ou une femme?)   */
    /*   if (genre == "Homme"){
           tabHypo =new string ['V01', 'V02', 'V03'];
       }
       else if (genre == "Femme"){
           tabHypo = new string ['V01', 'V02'];
       }*/

    float min;

    string  nomfichier;
    string indice;

    /*      Cf WavToMfcc.h      */
    wavfile w2;
    FILE* f2;
    float* bufferMotCherche2 = NULL;
    int tailleBufferMC2 = 0;
    int16_t *data2;
    int sizeData2;

    //  for(int i=0; i<tabHypo->length(); i++){

    /*  Initialisation à zero de la matrice confusion (2dimensions??)   */
    /*for (int j=0; j<size_t(tabHypo); j++){
        matriceconfu[j]=0;
    }*/
    //locuteur = tabHypo[i];

    min=numeric_limits<float>::infinity();
    for (int j=0; j<tabMot->length(); j++){

        nomfichier = ("../res/raw/m01_"+tabMot[j]+".wav");
        //nomfichier = ("./corpus/dronevolant_bruite/M01_"+tabMot[j]+".wav");

        /*      Parametrisation des mots    */
        p = new char[nomfichier.length()+1];
        strcpy( p, nomfichier.c_str());

        wavRead(&f2, p, &w2);

        sizeData2= (w2.bytes_in_data / sizeof(int16_t));
        data2 = new int16_t[sizeData2];

        if (fread(&data2[0], sizeof(int16_t), sizeData2, f2) < 1){
            fprintf(stderr, "Can't read input wav data %s\n", &p);
            exit(1);
        }

        delete[] p;

        computeMFCC(&bufferMotCherche2, &tailleBufferMC2, data2, sizeData2, w2.frequency, 512, 256, 13, 26);


        /*      Récupération du résultat du dtw     */

        resDTW = dtw(tailleBufferMC2, tailleBufferMC, 13, bufferMotCherche2, bufferMotCherche);

        cout << "Mot : "<<tabMot[j]<< "		Distance avec le mot cherché : "<<resDTW<<endl;

        /*  Chercher le mot qui a la distance la plus petite avec notre enregistrement  */
        if(resDTW<min){
            min = resDTW;
            indice = tabMot[j];
        }

        //matriceconfu[j] = 1;

    }
    /* On renvoie le mot à la distance la plus petite   */
    cout <<"Minimum trouvé : "<< min << endl;
    if (min > 6.5){
        indice = "pas trouvé";
    }
    return env->NewStringUTF(indice.c_str());

    //  }

}
/*
int main(){
    string mot;
    mot = recoVocal("Homme", "../res/raw/dronevolant_nonbruite/F01_avance.wav");
    //mot = recoVocal("Homme", "./m01_arretetoi.wav");
    cout << "Le mot trouvé est : "<< mot << endl;

    return(0);
}
*/
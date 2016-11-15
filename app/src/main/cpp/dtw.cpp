/*******************************************************************************
 *
 * Drone control through voice recognition -- PC to drone communication
 * Team GYTAM, feb. 2016
 *
 *
 ******************************************************************************/

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h> // for memcmp
#include <stdint.h> // for int16_t and int32_t
#include <math.h>
#include <iostream>
//#include "dtw.h"
#include <cmath>
using namespace std;
float distance(float* c_k, float* c_unk, int indicei, int indicej) {
    return(abs(c_unk[indicej] - c_k[indicei]));
}

float distance_vect(float* c_k, float* c_unk, int indicei, int indicej, int dim_mfcc) {
    float d = 0;
	int i;

    float x[dim_mfcc];
    float y[dim_mfcc];

	for (i = 0 ; i < dim_mfcc ; i++)
		x[i] = c_k[indicei + dim_mfcc*i];

	for (i = 0 ; i < dim_mfcc ; i++)
		y[i] = c_unk[indicej + dim_mfcc*i];

    for (i = 0 ; i < dim_mfcc; i++)
        d = d + pow((x[i] - y[i]),2);
    
    return(sqrt(d));
}

float min(float a, float b, float c) {
    if ( a < b ) {
        if ( a < c ) {
            return a;
        } else {
            return c;
        }
    } else {
        if ( b < c ) {
            return b;
        } else {
            return c;
        }
    }
}

/**
* Dtw function that given two matrix of cep coefficient computes distance
* between those two signals.
*  @param n_ck      Dimension of unknow signal
*  @param n_cunk    Dimension of know signal
*  @param dim_mfcc  Size of nfcc decompostion base
*  @param c_k       Matrix of know signal
*  @param c_unk     Matrix of unknow signal
*  @return Distance between the two signals
*/

float dtw(int n_ck, int n_cunk, int dim_mfcc, float* c_k, float* c_unk) {

    float inf = 999999999999.9f;

    float w0 = 1;
    float w1 = 1;
    float w2 = 1;

    float dist;
    float v1,v2,v3;

    float matriceAux[n_cunk+1][n_ck+1];

    for (int i = 0; i < n_cunk+1; i++) {
        for (int j = 0; j < n_ck+1; j++) {
            if ((j == 0) || (i == 0)) matriceAux[i][j] = inf;
            else matriceAux[i][j] = 0;
        }
    }
    matriceAux[0][0] = 0;

    for (int i = 1; i < n_cunk+1; i++) {
        for (int j = 1; j < n_ck+1; j++) {
            dist = distance_vect(c_unk,c_k,i-1,j-1,dim_mfcc);
            v1 = matriceAux[i-1][j] + w0*dist;
            v2 = matriceAux[i-1][j-1] + w1*dist;
            v3 = matriceAux[i][j-1] + w2*dist;

            matriceAux[i][j] = min(v1,v2,v3);
        }
    }

    return((matriceAux[n_cunk][n_ck])/(n_ck+n_cunk));
}


int main () {
    float c_k[9] = {-2,10,-10,15,-13,20,-5,14,2};
    float c_unk[6] = {3,-13,14,-7,9,-2};


   float result = dtw(9,6,3,c_k,c_unk);

    std:cout << "d = " << result << std::endl;

    return(0);
}

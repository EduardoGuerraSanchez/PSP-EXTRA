#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>
#include <iostream>
#include <signal.h>
#include <sys/wait.h>
#include <cstdlib>
#include <string>
#include <string.h>
#include <sstream>
#include <fcntl.h>

#define RESTORE "\033[1;0m"
#define RED "\033[1;31m"
#define GREEN "\033[1;32m"
#define YELLOW "\033[1;33m"
#define CYAN "\033[1;36m"

using namespace std;

int main(){

    pid_t pid1, pid2;

    int fd1[2];
    int fd2[2];
    int fd3[2];
    int fd4[2];
    int fd5[2];

    long long int tamanio = 10;
    char cadena[tamanio];
    bool existe = false;
    bool insertar = false;
    //string cadenaPalabras[numeroLetras] = {"casa","boli","libro"};
    string* cadenaPalabras = new string[tamanio]{"casa","boli","libro"};

    pipe(fd1);
    pipe(fd2);
    pipe(fd3);
    pipe(fd4);
    pipe(fd5);

    pid1 = fork();

    switch (pid1){

    case -1:
        std::cerr << "No se ha asignado memoria correctamente" << '\n';
        exit(1);

    case 0: //Hijo

        pid2 = fork();

        switch (pid2){

        case -1:
            std::cerr << "No se ha asignado memoria correctamente" << '\n';
            exit(1);

        case 0: //Nieto
        cout << "NIETO" << endl;
        //while(1){
            existe = false;
            insertar = false;

            close(fd2[1]);
            read(fd2[0],&existe,sizeof(existe));

            if(existe == 0){
              close(fd3[1]);
              read(fd3[0],&cadena,sizeof(cadena));
              cout << GREEN << "Ma llegao: " << cadena << RESTORE << endl;

            }

            cout << GREEN << "Y ESTO ES: " << existe << RESTORE << endl;

            if(existe == 0){
              cout << GREEN << "Esta palabra no existe en el vector, vamos a proceder a insertarla..." << RESTORE << endl;
              for(int contador = 0;contador < tamanio && insertar == false;contador++){

                if(cadenaPalabras[contador] == ""){
                  cadenaPalabras[contador] = cadena;
                  cout << GREEN << "insertao: " << cadena << RESTORE << endl;
                  insertar = true;
                }
              }
            }
            else{
              cout << GREEN << "Tu palabra ya existia en el vector" << RESTORE << endl;
            }

            for(int contador = 0;contador < tamanio;contador++){
              cout << GREEN << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE << endl;
            }

            close(fd4[0]);
            //write(fd4[1],&cadenaPalabras,sizeof(cadena));

            cout << GREEN << "EL NIETO HA REALIZADO LA TERCERA PARTE" << RESTORE << endl;

            exit(0);
          //}

        break;

        default: //Hijo
          //while(1){
            cout << "HIJO" << endl;

            close(fd1[1]);
            read(fd1[0],&cadena,sizeof(cadena));

            cout << CYAN << "TU PALABRA ES: "  << cadena << RESTORE << endl;
            for(int contador = 0;contador < tamanio;contador++){

              if(cadenaPalabras[contador] == cadena){

                cout << CYAN << "LA PALABRA SE ENCUENTRA EN EL VECTOR" << RESTORE << endl;
                existe = true;
              }
            }
            close(fd2[0]);
            write(fd2[1],&existe,sizeof(existe));

            if(existe == 0){
              close(fd3[0]);
              write(fd3[1],&cadena,sizeof(cadena));
            }

            cout << CYAN << "AHI TE LO MANDO: " << existe << RESTORE << endl;
            cout << CYAN << "EL HIJO HA COMPLETADO LA SEGUNDA PARTE" << RESTORE << endl;

            pid2 = wait(NULL);

            close(fd4[1]);
          //  read(fd4[0],&cadenaPalabras,sizeof(tamanio));

            cout << CYAN << "AQUI ESTA TU VECTOR" << endl;
            for(int contador = 0;contador < tamanio;contador++){
              cout << CYAN << cadenaPalabras[contador] << RESTORE << endl;
            }

            close(fd5[0]);
            //write(fd5[1],&cadena,sizeof(cadenaPalabras));
            cout << CYAN << "EL HIJO HA REALIZADO LA CUARTA PARTE" << RESTORE << endl;

            exit(0);
          //}
      }

    default:
    //while(1){

        sleep(1);
        cout << "PADRE" << endl;

        cout  << YELLOW << "INTRODUCE: " << RESTORE << endl;
        //cin >> cadena[numeroLetras];
        cin.getline(cadena,tamanio);
        close(fd1[0]);
        write(fd1[1],&cadena,sizeof(cadena));

        cout << YELLOW << "EL PADRE HA REALIZADO LA PRIMERA PARTE" << RESTORE << endl;

        pid1 = wait(NULL);

        close(fd5[1]);
        //read(fd5[0],&cadena,sizeof(cadenaPalabras));

        for(int contador = 0;contador < tamanio; contador++){
          cout << YELLOW << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE<< endl;
        }

        cout << YELLOW << "EL PADRE HA REALIZADO LA ULTIMA PARTE" << YELLOW << endl;

        exit(0);
      //}
  }
}

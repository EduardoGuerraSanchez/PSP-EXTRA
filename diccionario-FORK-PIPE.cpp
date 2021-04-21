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
    int fd6[2];
    int fd7[2];
    int fd8[2];
    int fd9[2];

    long long int tamanio = 10;
    char cadena[tamanio];
    bool existe = false;
    char palabra[tamanio];

    bool fin = false;//Por si el usuario introduce un numero
    int contador = 0;
    string* cadenaPalabras = new string[tamanio]{"casa","boli","libro"};

    pipe(fd1);
    pipe(fd2);
    pipe(fd3);
    pipe(fd4);
    pipe(fd5);
    pipe(fd6);
    pipe(fd7);
    pipe(fd8);
    pipe(fd9);

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


            close(fd7[1]);
            read(fd7[0],&fin,sizeof(fin));

            if(fin == 1){
              close(fd4[0]);
              write(fd4[1],&existe,sizeof(existe));
            }
            else{
              close(fd2[1]);
              read(fd2[0],&existe,sizeof(existe));

              if(existe == 0){

                close(fd3[1]);
                read(fd3[0],&palabra,sizeof(palabra));

                cout << GREEN << "Esta palabra no existe en el vector, vamos a proceder a insertarla..."<<RESTORE<< endl;
                bool insertar = false;

                for(int contador = 0;contador < tamanio && insertar == false;contador++){

                  if(cadenaPalabras[contador] == ""){
                    cadenaPalabras[contador] = palabra;
                    insertar = true;
                  }
                }
              }
              else{
                cout << GREEN << "Tu palabra ya existia en el vector" << RESTORE << endl;
              }

              for(int contador = 0;contador < tamanio;contador++){
                if(cadenaPalabras[contador] != ""){
                  cout << GREEN << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE << endl;
                }
              }
              cout << endl;

              close(fd4[0]);
              write(fd4[1],&existe,sizeof(existe));
            }

            close(fd8[0]);
            write(fd8[1],&fin,sizeof(fin));


            exit(0);


        break;

        default: //Hijo

            close(fd6[1]);
            read(fd6[0],&fin,sizeof(fin));

            if(fin == 0){
              close(fd1[1]);
              read(fd1[0],&palabra,sizeof(palabra));

                for(int contador = 0;contador < tamanio;contador++){

                  if(cadenaPalabras[contador] == palabra){

                    cout << CYAN << "LA PALABRA SE ENCUENTRA EN EL VECTOR" << RESTORE << endl;
                    existe = true;
                  }
                }
                close(fd2[0]);
                write(fd2[1],&existe,sizeof(existe));

                if(existe == 0){
                  close(fd3[0]);
                  write(fd3[1],&palabra,sizeof(palabra));
                }

            }
            else{

            }
            close(fd7[0]);
            write(fd7[1],&fin,sizeof(fin));

            pid2 = wait(NULL);

            close(fd4[1]);
            read(fd4[0],&existe,sizeof(existe));

            close(fd8[1]);
            read(fd8[0],&fin,sizeof(fin));

        if(fin == 0){

          if(existe == 1){
              for(int contador = 0;contador < tamanio;contador++){
                if(cadenaPalabras[contador] != ""){
                  cout << CYAN << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE << endl;
                }
              }
              cout << endl;

              close(fd5[0]);
              write(fd5[1],&existe,sizeof(existe));
          }
          else{
            for(int contador = 0;contador < tamanio;contador++){
              if(cadenaPalabras[contador] != ""){
                cout << CYAN << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE << endl;
              }
            }
            cout << endl;
          }
        }
          else{

            }
            close(fd9[0]);
            write(fd9[1],&fin,sizeof(fin));

            exit(0);

      }

    default:
        sleep(1);

        cout  << YELLOW << "INTRODUCE LA PALABRA QUE DESEAS: " << RESTORE << endl;

      cin >> palabra;

      for(int contador = 0;contador < tamanio && fin == false;contador++){

        if(isdigit(palabra[contador])){
          fin = true;
        }

      }

      if(fin == true){
        close(fd6[0]);
        write(fd6[1],&fin,sizeof(fin));
      }

      else{
        close(fd1[0]);
        write(fd1[1],&palabra,sizeof(palabra));

        close(fd6[0]);
        write(fd6[1],&fin,sizeof(fin));
      }

        pid1 = wait(NULL);

        close(fd5[1]);
        read(fd5[0],&existe,sizeof(existe));

        close(fd9[1]);
        read(fd9[0],&fin,sizeof(fin));

        if(fin == 1){
          cout << RED << "HASTA AQUI HEMOS llegao" << RESTORE << endl;
        }
        else{
          if(existe == 0){
            cout << RED << "TU PALABRA NO EXISTIA EN EL VECTOR, A SI QUE LA HEMOS AÑADIDO" << RESTORE << endl;
            cout << endl;
            for(int contador = 0;contador < tamanio; contador++){
              if(cadenaPalabras[contador] != ""){
                cout << RED << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE<< endl;
              }
            }
          }
          else{
            cout << GREEN << "TU PALABRA YA EXISTIA EN EL VECTOR, A SI QUE AQUI TIENES " << RESTORE << endl;
            cout << endl;
            for(int contador = 0;contador < tamanio; contador++){
              if(cadenaPalabras[contador] != ""){
                cout << GREEN << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE<< endl;
              }
            }
          }
          cout << endl;
        }

        exit(0);
  }
}

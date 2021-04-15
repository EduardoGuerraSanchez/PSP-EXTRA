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
    bool insertar = false;
    char palabra[tamanio];

    bool fin = false;
    int contador = 0;
    bool adelante = false;
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
        //cout << GREEN << "NIETO" << RESTORE << endl;
        //while(1){
          //  existe = false;
            insertar = false;
            fin = false;

            close(fd7[1]);
            read(fd7[0],&fin,sizeof(fin));

            //cout << "-----------------------------" << fin << endl;

            if(fin == 1){
            //  cout << "TE HA MANDADO UN NUMERO" << endl;
              close(fd4[0]);
              write(fd4[1],&existe,sizeof(existe));
            }
            else{
            //  cout << "TODO BIEN HAS PUESTO UNA LETRA" << endl;
              close(fd2[1]);
              read(fd2[0],&existe,sizeof(existe));

              if(existe == 0){

                close(fd3[1]);
                read(fd3[0],&palabra,sizeof(palabra));
              //  cout << GREEN << "Ma llegao: " << cadena << RESTORE << endl;

                cout << GREEN << "Esta palabra no existe en el vector, vamos a proceder a insertarla..."<<RESTORE<< endl;

                for(int contador = 0;contador < tamanio && insertar == false;contador++){

                  if(cadenaPalabras[contador] == ""){
                    cadenaPalabras[contador] = palabra;
                    //cout << GREEN << "insertao: " << cadena << RESTORE << endl;
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

          //  cout << GREEN << "EL NIETO HA REALIZADO LA TERCERA PARTE" << RESTORE << endl;

            exit(0);
          //}

        break;

        default: //Hijo
          //while(1){
        //    cout << CYAN << "HIJO" << RESTORE << endl;
            fin = false;

            close(fd6[1]);
            read(fd6[0],&fin,sizeof(fin));

          //  cout << "EN EL HIJO FIN ES: " << fin << endl;

            if(fin == 0){
          //    cout << "HAS PUESTO UNA PALABRA" << endl;
              close(fd1[1]);
              read(fd1[0],&palabra,sizeof(palabra));

              //  cout << CYAN << "TU PALABRA ES: "  << cadena << RESTORE << endl;
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
          //    cout << "HAS PUESTO UN NUMERO" << endl;

            }
            close(fd7[0]);
            write(fd7[1],&fin,sizeof(fin));
          /*  close(fd1[1]);
            read(fd1[0],&palabra,sizeof(palabra));

          //  cout << CYAN << "TU PALABRA ES: "  << cadena << RESTORE << endl;
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
            }*/

          //  cout << CYAN << "AHI TE LO MANDO: " << existe << RESTORE << endl;
        //    cout << CYAN << "EL HIJO HA COMPLETADO LA SEGUNDA PARTE" << RESTORE << endl;

            pid2 = wait(NULL);

            close(fd4[1]);
            read(fd4[0],&existe,sizeof(existe));

            close(fd8[1]);
            read(fd8[0],&fin,sizeof(fin));

        //    cout << "esto es: " << existe << endl;

        if(fin == 0){

          if(existe == 1){
        //    cout << "PALABRAAAAA" << endl;
            //  cout << CYAN << "AQUI ESTA TU VECTOR" << endl;
              for(int contador = 0;contador < tamanio;contador++){
                if(cadenaPalabras[contador] != ""){
                  cout << CYAN << "Posicion [" << contador << "] " << cadenaPalabras[contador] << RESTORE << endl;
                }
              }
              cout << endl;

              close(fd5[0]);
              write(fd5[1],&existe,sizeof(existe));
      //        cout << CYAN << "EL HIJO HA REALIZADO LA CUARTA PARTE" << RESTORE << endl;
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
        //      cout << "SEGUIMOS CON LOS NUMERICOS" << endl;
              close(fd8[0]);
              write(fd8[1],&fin,sizeof(fin));
            }
            close(fd9[0]);
            write(fd9[1],&fin,sizeof(fin));

            exit(0);
          //}
      }

    default:
    //while(1){
        sleep(1);
      //  cout << YELLOW << "PADRE" << RESTORE << endl;

        cout  << YELLOW << "INTRODUCE LA PALABRA QUE DESEAS: " << RESTORE << endl;

      //cin.getline(cadena,tamanio);
      cin >> palabra;

      for(int contador = 0;contador < tamanio && fin == false;contador++){

        if(isdigit(palabra[contador])){
    //      cout << "ES UN NUMERO" << endl;
          fin = true;
        }
        else{
    //      cout << "ES UNA LETRA" << endl;
          adelante = true;
        }

      }

      if(fin == true){
        close(fd6[0]);
        write(fd6[1],&fin,sizeof(fin));
      }

      if(adelante == true){
        close(fd1[0]);
        write(fd1[1],&palabra,sizeof(palabra));

        close(fd6[0]);
        write(fd6[1],&fin,sizeof(fin));
      }

    /*  while(fin == false){

        if(isalpha(palabra[contador])){

          cout << "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" << endl;
          close(fd6[0]);
          write(fd6[1],&fin,sizeof(fin));
        }

        else{
          cout << "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" << endl;

          close(fd1[0]);
          write(fd1[1],&palabra,sizeof(palabra));
          fin = true;

        }
        contador++;

      }*/

    //    cout << YELLOW << "EL PADRE HA REALIZADO LA PRIMERA PARTE" << RESTORE << endl;

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

    //    cout << YELLOW << "EL PADRE HA REALIZADO LA ULTIMA PARTE" << YELLOW << endl;

        exit(0);
      //}
  }
}

#include "sharemem.h"

int main(){
	int i,j,id,*ptr;
	int* tabpointeur[5];
	//Creation d'un segment de mémoire
	id=shmget(CLE,100,0666);
	//Attachement du segment
	ptr=shmat(id,NULL,0);

	//recuperation des adresses des pointeurs
	for(i=0;i<5;i++){
		tabpointeur[i]=ptr[i];
	}

	//affichage des mêmes éléments que shrpte
	//affichage des elements demandés
	printf("Adresse du premier element du tableau de pointeur = %x\n",&tabpointeur[0]);
	printf("Liste des éléments du tableau de pointeurs:\n");
	for(i=0;i<5;i++){
		printf("%d -> %x\n",i,tabpointeur[i]);
	}
	printf("contenu des vecteurs:\n");
	for(i=0;i<5;i++){
		printf("Adresse du premier element:%x\n",tabpointeur[i]);
		for(j=0;j<3;j++){
			printf("elt %d = %d\n",j,*(tabpointeur[i]+j));
		}
	}

	//detachement du segment de memoire partagee
	shmdt(ptr);


	return 0;
}

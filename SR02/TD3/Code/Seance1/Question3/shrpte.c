#include "sharemem.h"

int main(){
	int i,j,id,*ptr;
	int* tab[5]; //le tableau de pointeurs
	int tabentier[15]; //les vecteurs

	//initialisation tableau de pointeurs
	for(i=0;i<5;i++){
		tab[i]=&tabentier[i*3];
	}

	//initialisation du tableau de vecteurs
	for(i=0;i<15;i++){
		tabentier[i]=i;
	}

	//affichage des elements demandés
	printf("Adresse du premier element du tableau de pointeur = %x\n",&tab[0]);
	printf("Liste des éléments du tableau de pointeurs:\n");
	for(i=0;i<5;i++){
		printf("%d -> %x\n",i,tab[i]);
	}
	printf("contenu des vecteurs:\n");
	for(i=0;i<5;i++){
		printf("Addresse du premier element:%x\n",tab[i]);
		for(j=0;j<3;j++){
			printf("elt %d = %d\n",j,*(tab[i]+j));
		}
	}

	//Creation d'un segment de mémoire
	id=shmget(CLE,100,IPC_CREAT|0666);
	//Attachement du segment
	ptr=shmat(id,NULL,0);

	//ajout des elements dans le segment de memoire partagee
	for(i=0;i<5;i++){ //copie du tableau de vecteur
		ptr[i]=tab[i];
	}
	for(i=0;i<15;i++){ //copie des vecteurs
		ptr[i+5]=tabentier[i];
	}
	//attente de 40 secondes
	sleep(40);

	//detachement du segment de memoire partagee
	shmdt(ptr);
	//suppression du segment de memoire partagee
	shmctl(id,IPC_RMID,0);

	return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

pid_t pere,fils;
int NB_SIGINT=0;
int n;

void captpere(int num){
	NB_SIGINT++;
	printf("Temps restant: %d\n",n);
	if(NB_SIGINT>3) exit(0);
}

void captfils(int num){
	rectvert(2);
	NB_SIGINT++;
	if(NB_SIGINT>3) exit(0);
}

int main(void){
	pere=getpid();
	
	struct sigaction sa;
	sa.sa_handler=&captpere;
	sigaction(SIGINT,&sa,NULL);
	
	fils=fork();
	switch(fils){
		case 0:{
			sa.sa_handler=&captfils;
			sigaction(SIGINT,&sa,NULL);
			
			initrec();
			int i;
			while((i=attendreclic())!=-1){
				if(i==0)
					kill(pere,SIGINT);
			}
			break;
		}
		case -1:{
			printf("An error occured\n");
			break;
		}
		default:{
			while(1){
				n=sleep(10);
			}
		}
	}
	return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>


pid_t lespid[3];
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
	lespid[0]=getpid();
	struct sigaction sa;
	sa.sa_handler=&captpere;
	sigaction(SIGINT,&sa,NULL);
	lespid[1]=fork();
	//fils=fork();
	switch(lespid[1]){
		case 0:{
			sa.sa_handler=&captfils;
			sigaction(SIGINT,&sa,NULL);
			
			//creation du second fils
			lespid[2]=fork();
			if(lespid[2]==0){
				initrec();
				int i;
				while((i=attendreclic())!=-1){
					if(i==3){
						kill(lespid[0],SIGINT);
						kill(lespid[1],SIGINT);
						kill(lespid[2],SIGINT);
					}
					else
						kill(lespid[i],SIGINT);
				}
			}else if (lespid[2]==-1){
				printf("An error occured while creating the second process");
			}else{
			//le premier fils
				initrec();
				int i;
				while((i=attendreclic())!=-1){
					if(lespid[2]!=-1 && i==2)
						kill(lespid[2],SIGINT);
					else if(i==3){
						if(lespid[2]!=-1)
							kill(lespid[2],SIGINT);
						kill(lespid[0],SIGINT);
						kill(lespid[1],SIGINT);
					}
					else
						kill(lespid[i],SIGINT);
				}
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

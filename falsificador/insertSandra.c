#include <stdlib.h>
#include <string.h>


struct set {
	int capacity;
	int size;
	int elem[3]; //calloc() inicializado a 0
};

// CONSTRUIR UNA ESTRUCTURA DE UN CIERTO TAMAÑO
struct set* new(int capacity) {
	struct set *new_set;																									
	
	new_set = (struct set*) malloc(sizeof(struct set));									
	if(new_set == NULL)														
		return NULL; /* no memory left */									
	
	new_set->capacity = capacity;											
	new_set->size = 0;	
	*(new_set->elem) = malloc(3 * sizeof(int));					
	//new_set->elem[0] = NULL;	//si no inicializo en el struct MEMORY ERROR: out of bound pointer
								//debido a que no esta inicializado es NULL
	return new_set;															
}


int insert(struct set *s, int x) {														
	int found;
	int i;

	if(x==NULL)
		return 0; 
	if(s==NULL)																
		return 0; /* NULL set */									
		
	if(s->size >= s->capacity)												
		return 0; /* no space left */
		
	if(s->elem == NULL) { /* empty set */							
	
		s->elem[s->size] = x;		
		s->size = 1;
		
		return 1;
	}
    								
															
	found = 0;
	for(i = 0; i<3; i++) {		
		if(s->elem[i] != NULL){								
			if(s->elem[i] == x)	{												
				found = 1;
			}
		}													
	}
		
	if(found) {
		return 0; /* element already in the set */
	}
	
	/* Creation of new node */

	//new_node = (struct lnode*) malloc(sizeof(struct lnode));				
	//if(new_node == NULL)													
		//return 0; /* no memory left */										
											
	s->elem[s->size] = x;										
	s->size = s->size + 1;										
	
	return 1; /* element added */											
}

int isnull(struct set *s) {
	if(s==NULL)																
		return 1;															
	return 0;																
}

int isempty(struct set *s) {
	if(s==NULL)																
		return 0; 															
	if(s->elem==NULL)														
		return 1; /* s is empty */											
	return 0;																 
}

int isfull(struct set *s) {
	if(s==NULL)																
		return 0;															 
	if(s->size >= s->capacity)												
		return 1; /* s is full */											
	return 0; 																
}

int contains(struct set *s, int x) {	
    int i;									
	
	if(s==NULL)																
		return 0; /* s is NULL */											
																	
	for(i = 0 ; i < 3 ; i++){													
		if(s->elem[i] == x)													
			return 1; /* element found */																							
	}
	
	return 0; /* element NOT found */										
}

int length(struct set *s) {
	if(s==NULL)																
		return 0; /* s is NULL */								
	
	return s->size;															
}



int main(){
    struct set *listaElementos = new(3); //?¿?¿?¿?¿?
	int elementoInsertar;
	int lista[3];

	klee_make_symbolic(&elementoInsertar, sizeof(elementoInsertar), "elementoInsertar");
	klee_make_symbolic(&lista,sizeof(lista),"lista");
	//klee_make_symbolic(*listaElementos, sizeof(*listaElementos), "listaElementos");

	memcpy(listaElementos->elem,lista,sizeof(lista));
	
	
	klee_assume(contains(listaElementos,elementoInsertar) == 1);

	klee_assume(isfull(listaElementos) == 1);

	insert(listaElementos,elementoInsertar);

}
//memcpy copiar //////////////////////
	// CREAR ARRAY SIMBOLICO
	//INDEPENDIENTE Y COPIAR
	//STRUCT ENTERA SIMBOLICA
	//https://www.mail-archive.com/klee-dev@imperial.ac.uk/msg02807.html

	//CONTRASEÑA KLEE
	//MAILING LIST

	
/* 
	//PUNTERO INVALIDO
	klee_make_symbolic(listaElementos->elem, sizeof(listaElementos->capacity),"listaElementos");
	klee_make_symbolic(&listaElementos->elem, sizeof(listaElementos->capacity),"listaElementos");
	
	//SIZE MAL
	klee_make_symbolic(listaElementos, sizeof(), "listaElementos");
*/
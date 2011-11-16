/********************************************************************************
 * PrioQueue - Ist eine linear geordnete Menge von Atomen						*
 * 			 - Wird als inhomogene, sortierte verkettete Liste implementiert	*
 * 			 																	*
 *      Author: Tobias Mayer - Mayer25@hm.edu									*
 *      																		*
 * Usage: In Krankenhäusern kann damit eine Patienten-Liste angelegt werden		*
 * 		  welche e Patient nach deren Priorität sortiert.     					*
 * ******************************************************************************/
#include "liste.h"

class PrioQueue
{
private:
	int size; //anzahl an Listenelementen
	Cell* ptr; //Zeiger auf das Element mit der größten Priorität
public:
	PrioQueue();
	PrioQueue(int s,Cell* p){size = s; ptr = p;}	//CCtor
	PrioQueue& operator=(const PrioQueue& ref) //ZuweisungsOperator
	void Create(){size = 0; ptr = null;}//erzeugt eine leere Priority Queue
	void Insert(T prio,Cell* p){size++; if(ptr.getPriority() > prio)ptr = p;}// fügt ein Atom in die Priority Queue ein
	void display();
	~PrioQueue()		//DCtor
}


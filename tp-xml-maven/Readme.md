# Reponse aux questions de cours sur L'API Java pour XML

## Exercice 1

### Question 3 :
1. On ajoute une instance en faisant appel à la méthode **createDocument()** de la classe factory **DocumentHelper**
2. Le noeud racine se nomme **marin**
3. En faisant appel à la méthode **addElement()** sur le document
4. En faisant appel à la méthode **addAttribute()** sur le 

## Exercice 2 

### Question 2 :
1. On récupère l'element racine on peut ainsi passer par ce dernier pour acceder à tout autre élément
2. A l'aide de la méthode **attributes()** qui retourne tous les attributs
3. En appellant la méthode **attributeValue()** qui retourne la valeur de l'attribut si elle existe, null sinon
4. En utilisant la méthode **elements()** sur l'élément
5. A l'aide de la méthode **getText()**


## Exercice 4 

### Question 1 :
* Les fichiers WSDL se trouvent à l'adresse : **http://webservices.amazon.com/AWSECommerceService/[VERSION]/AWSECommerceService.wsdl**

### Question 2 :
Le noeud racine est **definitions**, son espace de nom par défault est : **http://schemas.xmlsoap.org/wsdl**

### Question 3 :
L'autre espace de nom associé est : **http://webservices.amazon.com/AWSECommerceService/2013-08-01**, il est associé au préfixe : **tns**
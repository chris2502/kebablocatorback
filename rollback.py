# Valentin Chaillou
# Script qui checke periodiquement le nombre d'erreurs 500 du serveur back

import urllib.request
import time
import os
from subprocess import call

URL_GET_ERROR = os.environ["URL_GET_ERROR"] # "http://localhost:8090/kebab/geterror"

# Nombre d'erreurs autorisees
MAX_ERRORS_LEVEL_1 = int(os.environ["MAX_ERRORS_LEVEL_1"])
MAX_ERRORS_LEVEL_2 = int(os.environ["MAX_ERRORS_LEVEL_2"])
MAX_ERRORS_LEVEL_3 = int(os.environ["MAX_ERRORS_LEVEL_3"])

# Temps pour chaque niveau (en minutes)
TIME_LEVEL_1 = int(os.environ["TIME_LEVEL_1"])
TIME_LEVEL_2 = int(os.environ["TIME_LEVEL_2"])
TIME_LEVEL_3 = int(os.environ["TIME_LEVEL_3"])

# Frequence des checks (nombre de checks par minute)
CHECKS_LEVEL_1 = int(os.environ["CHECKS_LEVEL_1"])
CHECKS_LEVEL_2 = int(os.environ["CHECKS_LEVEL_2"])
CHECKS_LEVEL_3 = int(os.environ["CHECKS_LEVEL_3"])

PROPERTIES_LEVELS = [
	{
		"time": TIME_LEVEL_1, 
		"errors": MAX_ERRORS_LEVEL_1, 
		"checks": CHECKS_LEVEL_1
	},
	{
		"time": TIME_LEVEL_2, 
		"errors": MAX_ERRORS_LEVEL_2, 
		"checks": CHECKS_LEVEL_2
	},
	{
		"time": TIME_LEVEL_3, 
		"errors": MAX_ERRORS_LEVEL_3, 
		"checks": CHECKS_LEVEL_3
	}
]

# Juste pour être sûr que le serveur ait eu le temps de demarrer
# Au-delà, il y a un probleme
time.sleep(60) # secondes

# On arrange les temps pour exclure les durees des niveaux precedents
for i in range(1, len(PROPERTIES_LEVELS)):
	PROPERTIES_LEVELS[i]["time"] -= PROPERTIES_LEVELS[i-1]["time"]

def rollback():
	print("Allez je me casse. Salut !")
	call(["heroku", "rollback"])
	exit()

# 3 niveaux
# 1er niveau : pas plus de 20 erreurs en 10 minutes
# 2eme niveau ; pas plus de 100 erreurs en 40 minutes
# 3eme niveau : pas plus de 150 erreurs en 120 minutes
for levelProperty in PROPERTIES_LEVELS:
	for i in range(0, levelProperty["time"] * levelProperty["checks"]):
		toRollback = False
		try:
			nbErreurs = int(urllib.request.urlopen(URL_GET_ERROR).read())
			if nbErreurs > levelProperty["errors"]:
				toRollback = True
				print("Erreurs : " + str(nbErreurs) + " en " + str(i*60/levelProperty["checks"]) + " secondes. Preparation pour rollback...")
			else:
				toRollback = False
				print("Erreurs : " + str(nbErreurs) + " en " + str(i*60/levelProperty["checks"]) + " secondes")
		except:
			toRollback = True
			print("Connexion perdue. Preparation pour rollback...")
		if toRollback:
			rollback()
		
		time.sleep(60/levelProperty["checks"])
	print("\n************************\nNiveau passe avec succes\n************************\n")
			
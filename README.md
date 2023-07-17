# Authapp

Résumé du contenu de l’application

La première page d’activité de mon application permet à l’utilisateur de s’authentifier et au besoin de changer son mot de passe ou de s’inscrire s’il ne l’est pas déjà. 

D’abord, pour se connecter, l’usager peut entrer son nom d’utilisateur, son mot de passe et appuyer sur le bouton de connexion. S’il n’est pas inscrit et qu’il essaye de se connecter, une erreur s’affichera. L’erreur affichée expliquera que le nom d’utilisateur ou le mot de passe n’est pas valide et que s’il n’est pas inscrit, il doit appuyer sur « S’inscrire ». Alors pour s’enregistrer, il doit faire le même processus que pour se connecter, mais il doit appuyer sur le bouton « S’inscrire » et une fenêtre de dialogue s’affichera pour lui indiquer que son inscription a bien été effectuée. Ainsi, il pourra alors se connecter.

Une fois l’usager identifié, il est redirigé sur la seconde activité, qui lui permet de voir les citations de 10 personnages de jeux vidéo faisant partie de la série Final Fantasy. Les informations seront représentées dans un RecyclerView qui affichera donc le nom du personnage en question, le nom de la série dans lequel il se trouve ainsi que la citation de ce dernier.

Une fois connecté, s’il le désire, il peut changer son mot de passe en appuyant sur « Changer le mot de passe » qui le redirigera sur une troisième activité consacrée à la modification de son mot de passe. L’usager devra alors écrire son nom d’utilisateur, son mot de passe actuel et le nouveau qu’il souhaite avoir pour ensuite confirmer le tout en appuyant sur le bouton du même nom (Confirmer).

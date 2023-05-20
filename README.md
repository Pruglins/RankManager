# Rank Manager

## Description

RM est un plugin qui permet de créer des grades personnalisés en lui donnant une couleur et des permissions.
Des préfixes sont ajoutés lorsque le joueur parle ou se connecte.

## Doc
### Commandes

`create (name) (color)`
- Création d'un grade en partant du nom et de la couleur.
- `name` est le nom du rang.
- `color` est une couleur sous le format : &X où X est entre 0 et 9 ou de a à f.
(voir [Codes Couleur Minecraft](https://htmlcolorcodes.com/fr/codes-couleur-minecraft/))
- Exemple : `/create VIP &6`

`remove (name)`
- Supprimer un grade.
- `name` est le nom du rang.
- Exemple : `/remove VIP`

`list`
- Affiche tous les grades disponibles.
- Exemple : `/list`

`spr (player name) (rank name)`
- (Set Player Rank) met un rang à un joueur
- Exemple : `/spr Program132 VIP`

`scr (rank name) (new color)`
- `rank name` est le nom du rang auparavant créé.
- `new color` est une couleur sous le format : &X où X est entre 0 et 9 ou de a à f.
  (voir [Codes Couleur Minecraft](https://htmlcolorcodes.com/fr/codes-couleur-minecraft/))
- (Set Color Rank) change la couleur du rang.
- Exemple : `/scr VIP &4`

`snr (rank name) (new rank name)`
- `rank name` est le nom du rang auparavant créé.
- `new rank name` est le nouveau nom du rang.
- (Set Name Rank) change le nom du rang.
- Exemple : `/snr VIP Vip`
- **ATTENTION** : Tous les joueurs ayant le rang possédant l'ancien nom ne verront pas leur rang disparaître.

`addperm (rank name) (permission)`
- `rank name` est le nom du rang auparavant créé.
- `permission` est la permission que vous voulez ajouter au grade.
- Ajoute une permission a un grade.
- Exemple : `/addperm VIP rm.list`

`removeperm (rank name) (permission)`
- `rank name` est le nom du rang auparavant créé.
- `permission` est la permission que vous voulez retirer au grade.
- Retire une permission a un grade.
- Exemple : `/removeperm VIP rm.list`

## Permissions

- rm.create
- rm.remove
- rm.list
- rm.spr
- rm.scr
- rm.snr
- rm.addperm
- rm.removeperm
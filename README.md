# Rank Manager

## Description

Plugin Minecraft mettant en place la création de grade en donnant un nom, une couleur, etc.

## Doc
### Commandes

`create (name) (color)`
- Création d'un grade en partant du nom et de la couleur.```
- `name` est un nom, 
- `color` est une couleur sous le format : &X où X est entre 0 et 9 ou de a à f.
(voir [Codes Couleur Minecraft](https://htmlcolorcodes.com/fr/codes-couleur-minecraft/))
- Exemple : `/create VIP &6`

`list`
- Affiche tous les grades disponibles.
- Exemple : `/list`

`spr (player_name) (rank_name)`
- (Set Player Rank) met un rang à un joueur
- Exemple : `/spr Program132 VIP`

## Permissions

- rm.create
- rm.list
- rm.spr
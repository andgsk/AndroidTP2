# -*- coding: utf-8 -*-

import json

from google.appengine.ext import ndb

# Classe User
class User(ndb.Model):
	# Requis
	str_username = ndb.StringProperty(required=True)
	str_password = ndb.StringProperty(required=True)
	int_compte = ndb.IntegerProperty(required=True)
	
	# Facultatif
	str_prenom = ndb.StringProperty(required=False)
	str_nom = ndb.StringProperty(required=False)
	str_email = ndb.StringProperty(required=False)
	str_adresse = ndb.StringProperty(required=False)
	str_telephone = ndb.StringProperty(required=False)

	# Methodes

	# Methode qui cree un utilisateur a partir du JSON.
	def UpdateFromJSON(self, json_data, update_requis=False):
		
		if 'str_username' in json_data:
			self.str_username = json_data['str_username']
		if 'str_password' in json_data:
			self.str_password = json_data['str_password']
		if 'int_compte' in json_data:
			self.int_compte = int(json_data['int_compte'])
		if 'str_prenom' in json_data:
			self.str_prenom = json_data['str_prenom']
		if 'str_nom' in json_data:
			self.str_nom = json_data['str_nom']
		if 'str_email' in json_data:
			self.str_email = json_data['str_email']
		if 'str_adresse' in json_data:
			self.str_adresse = json_data['str_adresse']
		if 'str_telephone' in json_data:
			self.str_telephone = json_data['str_telephone']

		return self

	# Methode qui retourne du JSON
	def GetDict(self, send_key=True, strip_mdp=True):

		user_dict = self.to_dict()

		if send_key:
			user_dict['gae_key'] = self.key.urlsafe()
		if strip_mdp:
			del user_dict['str_password']

		return user_dict

# Class Connexion Token
class Token(ndb.Model):
	key_user = ndb.KeyProperty(required=True)
	b_valide = ndb.BooleanProperty(required=True)
	urlsafe_key = ndb.StringProperty(required=True)

# Classe Piste
class Piste(ndb.Model):
	# Requis
	str_nom = ndb.StringProperty(required=True)
	int_type = ndb.IntegerProperty(required=True)
	int_actif = ndb.IntegerProperty(required=True)
	key_ouvreur = ndb.KeyProperty(required=True)
	# Faculatif
	str_description = ndb.StringProperty(required=False)
	str_datecreation = ndb.DateProperty(required=False)

class Critique(ndb.Model):

	# Requis
	int_rating = ndb.KeyProperty(required=True)
	key_user = ndb.KeyProperty(required=True)

	# Facultatif
	str_commentaire = ndb.KeyProperty(required=True)

# Classe Demande
class Demande(ndb.Model):

	#Requis
	str_ouvreur = ndb.KeyProperty(required=True)
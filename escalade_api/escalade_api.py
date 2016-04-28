# -*- coding: utf-8 -*-

import webapp2
import logging
import traceback
import datetime
import json

from google.appengine.ext import ndb
from google.appengine.ext import db

# Importation des modèles de données personnalisés.
from models import User, Piste


# Modifier pre transformation en JSON
def serialiser_pour_json(objet):

    if isinstance(objet, datetime.datetime):

        # Pour une date et heure, on retourne une chaîne
        # au format ISO (sans les millisecondes).
        return objet.replace(microsecond=0).isoformat()
    elif isinstance(objet, datetime.date):
        # Pour une date, on retourne une chaîne au format ISO.
        return objet.isoformat()
    else:
        # Pour les autres types, on retourne l'objet tel quel.
        return objet


class MainPageHandler(webapp2.RequestHandler):

    def get(self):
        self.response.headers['Content-Type'] = 'text/plain; charset=utf-8'
        self.response.out.write('Démo "Service Web REST avec' +
                                'Google App Engine" en fonction !!!')


# Methode qui permet d'aller chercher un user par username.
def UserGetByUsername(username):
	return User.query(User.str_username==username).get()


class UserHandler(webapp2.RequestHandler):

	def post(self):

		try:
			json_user = json.loads(self.request.body)
			
			# S'il y a effectivement du json, on transform en objet utilisateur.
			if json_user:
				user = UserGetByUsername(json_user['str_username'])
				
				#S'il n'y a pas de user, on en cree un.
				if not user:
					user = User()
					user.UpdateFromJSON(json_user, True)
					user.put()

				#On update les infos de l'utilisateur
				else:
					user.UpdateFromJSON(json_user)
					logging.info(user)

		# Erreurs lie au donnees JSON
		except (db.BadValueError, ValueError, KeyError):
			logging.error('%s', traceback.format_exc())
			self.error(400)

	def get(self, username=None):

		try:
			if username is not None:

				# Fetch d'un user.
				user = UserGetByUsername(username)

				# User non trouvé
				if (user is None):
					self.error(404)
					return

				# Retourne le JSON data sans le mdp.
				json_data = json.dumps(user.GetJSON(), default=serialiser_pour_json)

			self.response.set_status(200)
			self.response.headers['Content-Type'] = ('application/json;' +
													' charset=utf-8')
			self.response.out.write(json_data)

		except (db.BadValueError, ValueError, KeyError):
			logging.error('%s', traceback.format_exc())
			self.error(400)

		except Exception:
			logging.error('%s', traceback.format_exc())
			self.error(500)

	def delete(self, username):

		try:
			if username is not None:

				user = UserGetByUsername(username)

				if user is not None:	
					key = user.key
					key.delete()
					
			self.response.set_status(204)

		except (db.BadValueError, ValueError, KeyError):
			logging.error('%s', traceback.format_exc())
			self.error(400)

		except Exception:
			logging.error('%s', traceback.format_exc())
			self.error(500)

class UserAllHandler(webapp2.RequestHandler):

	def get(self, username=None):

		try:
			users = User.query().fetch()

			if len(users) <= 0:
				self.error(404)
				return

			list_json_users = []

			for user in users:
				list_json_users.append(user.GetDict())

			json_data = json.dumps(list_json_users)

			self.response.set_status(200)
			self.response.headers['Content-Type'] = ('application/json;' +
													' charset=utf-8')
			self.response.out.write(json_data)

		except (db.BadValueError, ValueError, KeyError):
			logging.error('%s', traceback.format_exc())
			self.error(400)

		except Exception:
			logging.error('%s', traceback.format_exc())
			self.error(500)
            
app = webapp2.WSGIApplication(
    [
        webapp2.Route(r'/',
                      handler=MainPageHandler,
                      methods=['GET']),
       	webapp2.Route(r'/user/username/<username>',
       				  handler=UserHandler,
       				  methods=['GET', 'DELETE']),
        webapp2.Route(r'/user',
        			  handler=UserHandler,
        			  methods=['POST']),
        webapp2.Route(r'/user/all',
        			  handler=UserAllHandler,
        			  methods=['GET'])
    ],
    debug=True)

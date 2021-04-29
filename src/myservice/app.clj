(ns myservice.app
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [myservice.domain.account :as Account]
            [myservice.domain.repository :as AccountRepository]
            ))

(s/defschema User
  {:name s/Str})

(s/defschema UserResponse
  {:message s/Str})

(def app (api
    (GET "/health" []
      (ok "OK"))

    (POST "/message" []
      :body [{:keys [name]} User]
      :return UserResponse
      (ok {:message (str "Hello " name)}))))

(defn create-new-account! [& args ]
  (let [new-account (apply account/create-new-account args)]
    (account-repository/add! new-account)))

(defn book-onto-agency! [account-id & args]
  (let [{:keys [version account] :as result} (account-repository/find account-id)
        booked-account (apply account/book-onto-agency (list* account args))]
    (account-repository/update! version booked-account)
    nil))


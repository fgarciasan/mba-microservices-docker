(ns myservice.domain.account)

(defrecord Account [account-id size agency-id])

(defn create-new-account [& {:keys [size agency-id] :as conta-data}]
  {:pre [(integer? size)
         (nil? agency-id)]}
  (map->Conta account-data))

(defn book-onto-agency [a-account & {:keys [agency-id]}]
  {:pre [(integer? agency-id)
         (nil? (:agency-id a-conta))]}
  (assoc a-account :agency-id agency-id))

(defn set-account-id [a-account account-id]
  {:pre [(not (:account-id a-account))]}
  (assoc a-account :account-id account-id))



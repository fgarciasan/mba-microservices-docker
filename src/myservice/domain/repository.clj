(ns myservice.domain.repository
    (:refer-clojure :exclude [find]))
  
  (def find nil)
  (def add! nil)
  (def update! nil)
  
  (defprotocol AccountRepository
    (-find [this account-id])
    (-add! [this a-account])
    (-update! [this concurrency-version a-account]))
  
  (defn set-implementation! [impl]
    (def find (partial -find impl))
    (def add! (partial -add! impl))
    (def update! (partial -update! impl)))
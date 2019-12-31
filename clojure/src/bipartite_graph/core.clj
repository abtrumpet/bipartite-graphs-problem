(ns bipartite-graph.core
  (:refer-clojure))

(def example-graph
  {:nodes [
   {:id 1 :relationships [2 6]}
   {:id 2 :relationships [1 3]}
   {:id 3 :relationships [2 4]}
   {:id 4 :relationships [3 5]}
   {:id 5 :relationships [4 6]}
   {:id 6 :relationships [5 1]}
    ]
  }
)

(def example-graph-2
  {:nodes [
   {:id 1 :relationships [2 6 9]}
   {:id 2 :relationships [1 3]}
   {:id 3 :relationships [2 4]}
   {:id 4 :relationships [3 5]}
   {:id 5 :relationships [4 6]}
   {:id 6 :relationships [5 1 9]}
   {:id 7 :relationships [6 8]}
   {:id 8 :relationships [5 7]}
   {:id 9 :relationships [1 6]}
    ]
  }
)

(defn bipartite?
  "Determines whether a given graph is bipartite."
  [graph]
  (let [nodes (:nodes graph)]
    (loop [cur-node (first nodes)
           rest-of-nodes (rest nodes)
           cur-key :a
           maps {:a [] :b []}]
      (if (some #(.contains (get maps cur-key) %) (:relationships cur-node))
        false
        (if (zero? (count rest-of-nodes))
          true
          (recur
           (first rest-of-nodes)
           (rest rest-of-nodes)
           (if (= :a cur-key)
             :b
             :a
             )
           (merge-with into maps {cur-key [(:id cur-node)]})))))))


(defn -main
  []
  (print example-graph)
  (print "\n\n")
  (print (bipartite? example-graph))
  (print "\n\n")
  (print example-graph-2)
  (print "\n\n")
  (print (bipartite? example-graph-2))
  (print "\n\n"))


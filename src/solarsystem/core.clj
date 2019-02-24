(ns solarsystem.core
  (:require [quil.core :refer :all]
            [quil.middleware :refer [fun-mode]])) ;;; defines the middleware you want.

(def planets [{:name "Mercury"               ;;; Array of planets
               :year 0.24 
               :size 5
               :distance 40}
              
              {:name "Venus" 
               :year 0.615 
               :size 10
               :distance 70}

              {:name "Earth" 
               :year 0.79 
               :size 10
               :distance 100}

              {:name "Mars" 
               :year 1.88                
               :size 8
               :distance 130}

              {:name "Jupyter" 
               :year 11.86
               
               :size 25
               :distance 160}
              
              {:name "Saturn" 
               :year 29.46 
               
               :size 20
               :distance 190}

              {:name "Uranus" 
               :year 84.02 
               
               :size 14
               :distance 220}

              {:name "Neptune" 
               :year 164.8
             
               :size 14
               :distance 250}])
(defn train
  []
  (println "Choo choo! ")) 

(defn day-to-angle [day year-length] 
  (let [year-fraction (/ day 365)
        orbit-fraction (/ year-fraction year-length)
        full-circle (* 2 PI)]
    (* orbit-fraction full-circle)))

(defn draw-planet [planet day] ;;; references the d in the other
  (let [d (:distance planet)
        s (:size planet)
        a (day-to-angle day (:year planet))
        ;;;a (* (/ ( / d 365)) (/ 2 PI))
        x (+ 300 (* d (sin a)))
        y (+ 300 (* d (cos a)))]
    (ellipse x y s s)))

  ;;;(ellipse (:distance planet) 200 (:size planet) (:size planet)))
     
(defn draw-orbit [p] (ellipse 400 300 (* 2 (:distance p)) (* 2 (:distance p))))

(defn setup []
  (frame-rate 7) ;;; speed
  (smooth)
  0) 

(defn update-state [day] (inc day)) ;;; so the  planets actually rotate
;;;

(def shape-size 20)

(defn draw-state []
  (background 55)
  (fill 170 190 20)
  (let [pos (* 0.01 (millis)) ;; use a factor to slow things down
        x (mod pos (+ (width) shape-size))] ;; let the shapes go off the screen, then wrap
     (doseq [y (range 0 (height) shape-size)]
       (triangle x y
                 x (+ y shape-size)
                 (+ x shape-size) (+ y shape-size)))))

(sketch
  :host "slider"
  :draw #'draw-state)

;;;

(defn draw [day]
  (background 55)
  (fill 255 255 0)
  (ellipse  300 300 50 50)
  (no-fill)
  (doseq [p planets] (draw-orbit p))
  (fill 192)
  (doseq [p planets] (draw-planet p day))) ;;;Draw all planets

(defsketch example
  :host "slider"
  :title "Example"
  :setup setup 
  :update update-state
  :draw draw draw-state
  :size [600 600]
  :middleware [fun-mode]) ;;;

(defn -main [& args])

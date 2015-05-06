(ns ^:figwheel-always maxwell.smart
    )

(enable-console-print!)

;; Browser
;; =======

(def browser (.-browser goog.labs.userAgent))

;; Note: All this fns can be safely memoized

(defn android-browser? []
  (.isAndroidBrowser browser))

(defn chrome? []
  (.isChrome browser))

(defn coast? []
  (.isCoast browser))

(defn firefox? []
  (.isFirefox browser))

(defn ie? []
  (.isIE browser))

(defn webview? []
  (.isIosWebview browser))

(defn safari? []
  (.isSafari browser))

(defn silk? []
  (.isSilk browser))

(defn opera? []
  (.isOpera browser))

;; TODO: The order should be optimized by browser popularity
(defn get-browser
  "Returns a keyword with the Browser
  Ex: :chrome, :safari, :firefox"
  []
  (cond
    (android-browser?) :android-browser
    (chrome?) :chrome
    (coast?) :coast
    (firefox?) :firefox
    (ie?) :ie
    (webview?) :webview
    (safari?) :safari
    (silk?) :silk
    (opera?) :opera
    :else :unknown))

;; Browser Version
;; ===============

(defn get-browser-version
  "Returns a string with the version in the vendor's format
   Ex: \"42.0.2311.135\" for Chrome"
  []
  (.getVersion browser))

(defn browser>=
  "Whether the running browser version is higher or the same (>=) as the 
   given version.
  (running-browser-version >= given-version)
  Ex: if the running version is 3 then (browser>= 2) -> (3 >= 2) -> true 
      if the running version is \"42.0.2311.135\" then
        (browser>= \"43\") -> (\"42.0.2311.135\" >= \"43\") -> false"
  [version]
  (.isVersionOrHigher browser version))

;; Engine
;; ======

(def engine (.-engine goog.labs.userAgent))

;; TODO: check Closure library version before using this 
(defn edge?
  "Included in the latest Closure Library"
  []
  (.isEdge engine))

(defn gecko? []
  (.isGecko engine))

(defn presto? []
  (.isPresto engine))

(defn trident? []
  (.isTrident engine))

(defn webkit? []
  (.isWebKit engine))

;; TODO: The order should be optimized by engine popularity
(defn get-engine
  "Returns a keyword with the Browser
  Ex: :chrome, :safari, :firefox"
  []
  (cond
    (gecko?) :gecko
    (presto?) :presto
    (trident?) :trident
    (webkit?) :webkit
    (edge?) :edge
    :else :unknown))

;; Engine Version
;; ==============

(defn engine-version
  "Gets the running engine version or \"\" if it can't be determined"
  []
  (.getVersion engine))

(defn engine>=
  "Whether the running engine version is higher or the same (>=) as the 
   given version.
  (running-engine-version >= given-version)
  Ex: if the running version is 3 then (engine>= 2) -> (3 >= 2) -> true 
      if the running version is \"537.36\" then
        (browser>= \"540\") -> (\"537.36\" >= \"540\") -> false"
  [version]
  (.isVersionOrHigher engine version))

;; Device
;; ======

;; This namespace is only present in the latest Google Closure Library

(def device (.-device goog.labs.userAgent))

(defn desktop? []
  (.isDesktop device))

(defn mobile? []
  (.isMobile device))

(defn tablet? []
  (.isTablet device))

(defn get-device
  "Returns a keyword with the device type.
  Ex: :desktop, :mobile, :tablet, :unknown"
  []
  (cond
    (desktop?) :desktop
    (mobile?) :mobile
    (tablet?) :tablet
    :else :unknown))

;; Platform

;; Only Present in latest Closure Library version

(def platform (.-platform goog.labs.userAgent))

(defn android? []
  (.isAndroid platform))

(defn chrome-os? []
  (.isChromeOS platform))

(defn iOS? []
  (.isIos platform))

(defn iPad? []
  (.isIpad platform))

(defn iPhone? []
  (.isIphone platform))

(defn iPod? []
  (.isIpod platform))

(defn linux? []
  (.isLinux platform))

(defn mac? []
  (.isMachintosh platform))

(defn windows? []
  (.isWindows platform))

;; TODO: correct versions for Docstring
(defn platform>=
  "Whether the running platform version is higher or the same (>=) as the 
   given version.
  (running-platform-version >= given-version)
  Ex: if the running version is 3 then (engine>= 2) -> (3 >= 2) -> true 
      if the running version is \"537.36\" then
        (browser>= \"540\") -> (\"537.36\" >= \"540\") -> false"
  [version]
  (.isVersionOrHigher platform version))

;; User Agent 
;; ==========

(def util (.-util goog.labs.userAgent))

(defn get-agent []
  (.getUserAgent util))

(defn agent->tuples [agent]
  (js->clj (.extractVersionTuples util agent)))

;; Stack Traces
;; ============

;; TODO: Implement a cross-browser stack serialization method

(def testing goog.testing)

(defn e->str [e]
  nil)

(defn e->map [e]
  {:msg (pr-str (.-message e))
   :stack (pr-str (.-stack e))})

;; User
;; ====

;; TODO: conditionally add platform & device
(defn spy
  "Returns a map with all available user info"
  []
  {:browser (get-browser)
   :engine (get-engine)
   :agent (get-agent)})

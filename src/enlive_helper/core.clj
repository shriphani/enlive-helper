(ns enlive-helper.core
  "Adding additional functionality to
   enlive."
  (:import (org.htmlcleaner HtmlCleaner)))

(def options-fn-map
  {:prune-tags '.setPruneTags
   :omit-comments '.setOmitComments
   :advanced-xml-escape '.advancedXmlEscape
   :trans-res-chars-to-ncr '.transResCharsToNCR
   :translate-special-entities '.translateSpecialEntities
   :trans-special-entities-to-ncr '.transSpecialEntitiesToNCR
   :recognize-unicode-chars '.recognizeUnicodeChars
   :use-cdata '.useCdata
   :omit-unknown-tags '.omitUnknownTags
   :treat-unkn-tags-as-content '.treatUnknTagsAsContent
   :omit-depr-tags '.omitDeprTags
   :treat-depr-tags-as-content '.treatDeprTagsAsContent
   :omit-cdata-outside-script-and-style '.omitCdataOutsideScriptAndStyle
   :omit-xml-declaration '.omitXmlDeclaration
   :omit-doctype-declaration '.omitDoctypeDeclaration
   :omit-xmlns-attributes '.omitXmlnsAttributes
   :omit-envelope '.omitEnvelope
   :use-empty-element-tags '.useEmptyElementTags
   :allow-multi-word-attributes '.allowMultiWordAttributes
   :allow-html-inside-attributes '.allowHtmlInsideAttributes
   :ignore-quest-and-exclam '.ignoreQuestAndExclam
   :namespaces-aware '.namespacesAware
   :hyphen-replacement '.hyphenReplacement
   :boolean-atts '.booleanAtts
   :node-by-xpath '.nodeByXpath})

(defn options->properties
  "Sets the desired properties on the
   HTMLcleaner object"
  [options props]
  (do (doseq [[k v] options]
        (if (nil? (options-fn-map k))
          (throw
           (Throwable. "Unsupported property"))
          (let [routine (options-fn-map k)]
            (eval (cons routine '(props v))))))
      props))

(defn process-page
  [page-src options]
  (let [cleaner   (new HtmlCleaner)
        props     (.getProperties cleaner)
        _         (options->properties options props)
        processed (.clean cleaner page-src)]
    (str
     "<"
     (.getName processed)
     ">"
     (.getInnerHtml cleaner processed)
     "</"
     (.getName processed)
     ">")))

(defn html-resource-steroids
  "A more flexible html-resource - inspired by
   html-cleaner's options. We invoke clj-xpath"
  [text & options]
  (let [processed (process-page text
                                (partition 2 options))]
    (-> processed
        java.io.StringReader.
        (html/html-resource))))


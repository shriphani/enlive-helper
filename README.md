# enlive_helper

Often while working with raw HTML, it is beneficial to have
the HTML parser perform operations like removing comments, ignoring
script tags etc. The excellent
[HtmlCleaner library](http://htmlcleaner.sourceforge.net/index.php) 
bundles a lot of routines that I have exposed for easy use with
enlive's routines.

## With Leiningen
```
[enlive-helper "0.1.0"]
```

## With Maven
```
<dependency>
  <groupId>enlive-helper</groupId>
  <artifactId>enlive-helper</artifactId>
  <version>0.1.0</version>
</dependency>
```

An example of what you can do:

```clojure
(use 'enlive-helper.core)

(html-resource-steroids 
 (java.io.StringReader. "<html><body><a>hi</a></body></html>") 
 :prune-tags "a")
```

And this returns:

```clojure
({:tag :html,
  :attrs nil,
  :content
  ("\n"
   {:tag :head, :attrs nil, :content nil}
   "\n"
   {:tag :body, :attrs nil, :content nil})})
```

As you can see, the <code>a</code> tag is not parsed.

## Usage

Take a stream (this needs fixing since html-resource accepts a lot
more) and then do this:

```clojure
(html-resource-steroids stream options)
```

The available options are:

```clojure
(:allow-html-inside-attributes
 :omit-envelope
 :prune-tags
 :use-cdata
 :trans-special-entities-to-ncr
 :node-by-xpath
 :trans-res-chars-to-ncr
 :allow-multi-word-attributes
 :omit-doctype-declaration
 :treat-depr-tags-as-content
 :omit-cdata-outside-script-and-style
 :omit-xmlns-attributes
 :omit-unknown-tags
 :omit-xml-declaration
 :recognize-unicode-chars
 :treat-unkn-tags-as-content
 :advanced-xml-escape
 :use-empty-element-tags
 :omit-comments
 :omit-depr-tags
 :hyphen-replacement
 :boolean-atts
 :translate-special-entities
 :namespaces-aware
 :ignore-quest-and-exclam)
```

A good description of what these options expect and do is available
in the
[HtmlCleaner documentation](http://htmlcleaner.sourceforge.net/parameters.php).

## License

Copyright © 2014 Shriphani Palakodety

Distributed under the CRAPL License (see LICENSE)

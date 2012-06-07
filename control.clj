(use '[control.commands])

(defcluster :lsmaven
  :user ~(System/getenv "USER")
  :addresses ["maven.likestream.net"])


(deftask :deploy "Deploy jar and pom to maven repo" []
  (ssh "rm -f /home2/maven/maven2/net/likestream/clansi/1.2.1/clansi-1.2.1.jar" :sudo true)
  (ssh "rm -f /home2/maven/maven2/net/likestream/clansi/1.2.1/clansi-1.2.1.pom" :sudo true)
  (ssh "mkdir -p /home2/maven/maven2/net/likestream/clansi/1.2.1" :sudo true)
  (scp "clansi-1.2.1.jar" "/home2/maven/maven2/net/likestream/clansi/1.2.1" :sudo true)
  (scp "pom.xml" "/home2/maven/maven2/net/likestream/clansi/1.2.1/clansi-1.2.1.pom" :sudo true)  
  (ssh "chown -R maven:daemon /home2/maven/maven2/net/likestream/clansi" :sudo true))

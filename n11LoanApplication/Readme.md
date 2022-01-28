#**N11 Java Bootcamp Bitirme Projesi**
    Kredi başvurusunda bulunan bir kullanıcı için random bir kredi notu dönen servisten kredi notu ve 
    maaş bilgisi ve teminat tutarı bilgileri ışığında kredi limit hesaplaması işlemi gerçekleştirilmiştir.
    kullanıcı kayıt olduğu anda Docker üzerinde imajı kurulu olan MongoDB ye kullanıcı kaydedilir
    akabinde kafka consumer çağrılarak kredi hesaplaması asenkron bir şekilde yapıldıktan sonra
    listener servisi ile kullanıcıya bilgilendirme yapılır. (Onaylanırsa) sonrasında işlem tamamlanır.
    kredi başvuru limit  ve teminat gibi bilgiler yine docker üzerinde imajı bulunan postgresql üzerinde
    tutulmaktadır. mali işlemli kritik bir süreç olduğu için ilişkisel veritabanı üzerinde yönetiminin
    uygun olacağından dolayı ilişkisel veritabanı seçilmiştir.kafka işlem sırasında hata alırsa ilgili
    işlem için log kaydı oluşturp death list çağrımı yapılmaktadır.

#**Docker Notları**
    Docker ile ilgili proje dosyasında dockerfile oluşturulmuştur. proje çalışmasında containerlar oluşmazsa
    docker klasörü altında mongoDb ve kafka için compose.yml dosyaları mevcut bulunmaktadır. InteliJ kullanıyorsanız
    doğrudan inteliJ üzerinden çalıştırılabilir. diğer durumda docker-compose up komutu ile windows kullanıcıları için 
    .yml dosyasının bulunduğu dizine gelerek çalıştırılabilir.
    PostgreSQl için: docker run --name server_name -p 5432:5432 -e  POSTGRES_PASSWORD=password -d postgres
    komutu ile çalışıtırılabilir. PostgreSql için; bilgisayarda hali hazırda bir postgresql kurulu ise container
    oluşssa da ilgili port dolu olduğu için uygulama hata verebilir. bu durumda port ve/veya server ismini 
    değiştirebilirsiniz. aksi bir durum ile tekrar karşılaşmanız durumunda application.properties dosyası içerisinden
    localde uygulama olarak kurulu postgresql host,servername, kullanıcı adı ve şifre bilgilerini girerek uygulamayı çalıştırabilirsiniz.

#**Kafka Notları**



#**N11 Java Bootcamp Bitirme Projesi**
    Kredi başvurusunda bulunan bir kullanıcı için random bir kredi notu dönen servisten kredi notu ve 
    maaş bilgisi ve teminat tutarı bilgileri ışığında kredi limit hesaplaması işlemi gerçekleştirilmiştir.
    kullanıcı kayıt olduğu anda Docker üzerinde imajı kurulu olan MongoDB ye kullanıcı kaydedilir
    akabinde kafka consumer çağrılarak kredi hesaplaması asenkron bir şekilde yapıldıktan sonra listener servisi ile 
    kullanıcıya bilgilendirme yapılır. (Onaylanırsa) sonrasında işlem tamamlanır. kredi başvuru limit  ve teminat gibi 
    bilgiler yine docker üzerinde imajı bulunan postgresql üzerinde tutulmaktadır. mali işlemli
    kritik bir süreç olduğu için ilişkisel veritabanı üzerinde yönetiminin uygun olacağından dolayı 
    ilişkisel veritabanı seçilmiştir.
    kafka işlem sırasında hata alırsa ilgili işlem için log kaydı oluşturp death list çağrımı yapılmaktadır.

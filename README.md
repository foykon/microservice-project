
# Microservice Marketing Project

Springboot ile yapılmış bir market microservice projesi. microservice lere ayrılmış her servis bir yükümlülük almış görev dağılımı yapılmıştır. Gerekli durumlarda birbirleri JWT yardımıyla iletişimde bulunarak request ve response işlenleri kullanılmaktadır. Event driver kullanılmıştır. Parent tarafından tüm istekler öncelikle filtrelenmektedir. Database olarak PostgreSql kullanılmıştır hibernate ile tablolar otomatik oluşturulmuştur. Örnek olarak bazı servislere test eklenmiştir bu işlem JUNIT ile kurgulanmıştır. Aynı şekilde basit düzeyde bazı servislere error handling loglama eklenmiştir.



## Service İçerikleri

user-management-service : Kullanıcı kontrolleri ve CRUD işlemleri

sale-service : Satış servisi ürün alma stok azaltma(product-service ile iletişim halinde) fatura hazırlama

report-service : faturaları pdf olarak çıktısını alabilme

product-service : ürün CRUD

discovery-server : service yönlendirmeleri
  
## Özellikler

- soft delete
- role based auth
- Sıralama, Filtreleme ve Sayfalama 
- Log4j ve jUnit5

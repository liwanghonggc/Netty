字符编解码相关

ASCII(American Standard Code for Information Interchange,美国信息交换标准代码),使用7bit来表示一个字符,共可以表示128种字符,升级到ISO-8859-1

ISO-8859-1,8bit表示一个字符,即用一个字节(byte)表示一个字符,共计可以表示256个字符,无法表示中文等,中国制定gb2312

gb2312,两个字节表示一个汉字,但是有些生僻字无法表示,升级到gbk

gbk,是gb2312的超集

gb18030,最完整的汉字编码集

big5,台湾制定,针对繁体制定

unicode,全世界所有语言的编码集,统一采用两个字节来表示一个字符,并不适合英文等国家,因为存在空间浪费

UTF(Unicode Translation Format),unicode是一种编码方式,而UTF-8是一种存储方式,UTF-8是Unicode的实现方式之一

UTF-8,变长字节表示形式,兼容ASCII与ISO-8859-1,一般来说,UTF-8会使用3个字节来表示一个中文
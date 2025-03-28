### 常见的Http状态码有哪些？

- 1xx：信息，服务器收到请求，需要请求者继续执行操作
    - 100 Continue：继续，客户端应继续其请求
    - 101 Switching Protocols：切换协议，服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议
- 2xx：成功，操作被成功接收并处理
    - 200 OK：请求成功。一般用于GET和POST请求
    - 201 Created：已创建。成功请求并创建了新的资源
    - 204 No Content：无内容。服务器成功处理了请求，但在返回的响应中不包含内容
- 3xx：重定向，需要进一步的操作以完成请求
    - 301 Moved Permanently：永久移动。请求的资源已被永久移动到新位置
    - 302 Found：临时移动。请求的资源临时被移动了
    - 304 Not Modified：未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源(可以使用客户端缓存内容)
- 4xx：客户端错误，请求包含语法错误或无法完成请求
    - 400 Bad Request：客户端请求的语法错误，服务器无法理解
    - 401 Unauthorized：请求要求用户的身份认证
    - 403 Forbidden：服务器理解请求客户端的请求，但是拒绝执行此请求
    - 404 Not Found：服务器无法根据客户端的请求找到资源（网页）
- 5xx：服务器错误，服务器在处理请求的过程中发生了错误
    - 500 Internal Server Error：服务器内部错误，无法完成请求
    - 501 Not Implemented：服务器不支持请求的功能，无法完成请求
    - 503 Service Unavailable：由于超载或系统维护，服务器暂时无法处理客户端的请求
    - 504 Gateway Timeout：网关超时，服务器作为网关或代理，但是没有及时从上游服务器收到请求

### Http请求包含哪些内容，请求头和请求体有哪些类型?

Http请求包含`请求头`、`请求行`和`请求体`。  
常见的请求头类型:

- General headers: 同时适用于请求和响应消息，但与最终消息传输的数据无关的消息头。
- Request Headers: 包含更多有关要获取的资源或客户端本身信息的消息头。
- Entity Headers：包含有关实体主体的更多信息，比如主体长(Content-Length)度或其MIME类型。

请求体的类型一般有：

- 表单（form-data）application/x-www-form-urlencoded，提交表单数据
- 多部分数据（Multipart data）multipart/form-data 用于上传文件或复杂表单数据
- JSON application/json
- XML application/xml
- 纯文本 text/plain

### Http中 GET 和 POST 的区别是什么?

|        | GET            | POST              |
|--------|----------------|-------------------|
| 数据传输方式 | 通过URL传递数据      | 通过请求体传递数据         |
| 数据大小   | 有大小限制，一般为2KB   | 没有大小限制            |
| 安全性    | 不安全，数据会显示在URL中 | 安全性较高，数据不会显示在URL中 |
| 幂等性    | 幂等             | 非幂等               |
| 可缓存性   | 可缓存            | 不可缓存              |
| 请求参数类型 | 只能发送ASCII字符    | 可以发送任何类型的数据       |
| 应用场景   | 获取数据，查询数据      | 提交数据，修改数据         |

**ps:幂等性指的是就是一次请求和多次请求同一个资源产生相同的结果。**

---

### Http1.0 和 Http2.0 有什么区别?

|       |        http1.0        |             http2.0              |
|:-----:|:---------------------:|:--------------------------------:|
| 连接方式  | 非持久连接(每次亲求都要单独建立TCP)  |               持久连接               |
|  请求头  |    每次请求都会携带完整的请求头     |       请求头压缩，只发送一次，后续请求头复用        |
| 传输效率  | 传输效率低，每次请求都会携带完整的请求头  | (二进制文件)传输效率高，请求头压缩，只发送一次，后续请求头复用 |
| 多路复用  | 不支持多路复用，每次请求都需要建立新的连接 |     支持多路复用，多个请求可以同时在一个连接上传输      |
| 服务器推送 |       不支持服务器推送        |        支持服务器推送(服务器可以推送资源)        |

### Http2.0 和 3.0 有什么区别?

|       |         http2.0          |              http3.0               |
|:-----:|:------------------------:|:----------------------------------:|
|  协议   |  基于TCP，使用`二进制分帧层`实现多路复用  | 基于UDP，使用 `QUIC协议`，提供类似TCP的可靠性和多路复用 |
| 安全角度  |   使用TLS(Https)加密，安全性较高   |     默认使用QUIC自带的`TLS 1.3`，加密为强制     |
| 连接建立  | TCP三次握手 + TLS握手，建立连接需要时间 | `QUIC协议` 集成了`建立连接`和`加密握手` ,连接建立速度快 |
| 解决的问题 | TCP队头阻塞（仍存在，在高延迟和丢包情况下）  |         通过QUIC协议避免TCP队头阻塞          |

**队头阻塞：一个连接同时只能有效地承载一个请求**  
HTTP/1.1 是一个纯文本协议，它只在有效荷载（payload）的前面附加头（headers），
在资源块（resource chunks）之间不使用分隔符。它不会进一步区分单个资源与其他资源。
HTTP 规定报文必须是“一发一收”，这就形成了一个先进先出的串行队列。

**http2.0的队头阻塞:** 使用 SPDY协议 作为 HTTP/2 的起点，并使用多路复用（单个连接上可以进行并行交错的请求和响应，之间互不干扰），
解决了队头阻塞的问题，不过 TCP 本身的队头阻塞是无法避免的，而且对其影响更大 ，因为多个同域名的请求都只会使用同一个 TCP
连接，不会有多个并行连接

### Https 和 Http 的区别是什么?

|       |      http      |        https        |
|:-----:|:--------------:|:-------------------:|
|  端口   |    默认端口是80     |      默认端口是443       |
|  协议   |      明文传输      | 加密传输 （SSL / TLS 协议） |
|  安全性  | 不安全，数据会显示在URL中 |  安全性较高，数据不会显示在URL中  |
|  协议   |     基于TCP      |      基于TCP+TLS      |
| SEO影响 |    降低展示位置排名    |        优先展示         |

---

### TCP 和 UDP 有什么区别？（简单）

|        |       TCP        |      UDP       |
|:------:|:----------------:|:--------------:|
|  连接性   |       面向连接       |      无连接       |
|  顺序保证  |     保证数据包的顺序     |   不保证数据包的顺序    |
|  可靠性   |    保证数据包的可靠性     |   不保证数据包的可靠性   |
|  头部大小  |   头部较大，至少20字节    |   头部较小，只有8字节   |
|   性能   |      低、延迟大       |     高、延迟小      |
| 数据传输模式 |     字节流传输模式      |    数据报传输模式     |
|  适用场景  | 文件传输、web浏览、邮件发送等 | 视频会议、直播、DNS查询等 |


### 说说 TCP 的三次握手？（简单）
三次握手是为了建立可靠的TCP连接，三次握手的过程如下：
1. 客户端发送一个 SYN（同步）包到服务器，表示客户端请求建立连接。
2. 服务器收到 SYN 包后，发送一个 SYN-ACK（同步-确认）包到客户端，表示服务器同意建立连接。
3. 客户端收到 SYN-ACK 包后，发送一个 ACK（确认）包到服务器，表示客户端已经收到服务器的确认。
- 过程：客户端 打招呼 “你好”， 服务端回应 “你好”， 客户端确认 “收到”

**为什么需要三次握手**
1. 为了防止历史重复连接的初始化（主要原因）
2. 为了同步客户端和服务端的序列号（次要原因）

### TCP 是用来解决什么问题？（简单）
TCP 是一个可靠的、面向连接的协议，它提供了数据传输的可靠性、顺序性和完整性。解决了**数据在不可靠的IP网络上的传输问题**
1. TCP 通过三次握手建立连接，确保双方都准备好进行数据传输。(连接管理)
2. TCP 使用序列号和确认号来确保数据包的顺序和完整性。(可靠性)
3. TCP 使用滑动窗口机制来控制数据的发送和接收速度，避免拥塞。(流量控制)
4. TCP 通过拥塞避免算法来防止网络过载。（拥塞控制）

# spring-grpc

### Overview:

Remote Procedure Calls (RPCs) provide a useful abstraction for building distributed applications and services. The libraries in this repository provide a concrete implementation of the gRPC protocol, layered over HTTP/2. These libraries enable communication between clients and servers using any combination of the supported languages.

more details.. https://grpc.io

### Interface

Developers using gRPC typically start with the description of an RPC service (a collection of methods), and generate client and server side interfaces which they use on the client-side and implement on the server side.

By default, gRPC uses Protocol Buffers as the Interface Definition Language (IDL) for describing both the service interface and the structure of the payload messages. It is possible to use other alternatives if desired.

#### Surface API

Starting from an interface definition in a .proto file, gRPC provides Protocol Compiler plugins that generate Client- and Server-side APIs. gRPC users typically call into these APIs on the Client side and implement the corresponding API on the server side.

##### Synchronous vs. asynchronous

Synchronous RPC calls, that block until a response arrives from the server, are the closest approximation to the abstraction of a procedure call that RPC aspires to.

On the other hand, networks are inherently asynchronous and in many scenarios, it is desirable to have the ability to start RPCs without blocking the current thread.

The gRPC programming surface in most languages comes in both synchronous and asynchronous flavors.

### Streaming

gRPC supports streaming semantics, where either the client or the server (or both) send a stream of messages on a single RPC call. The most general case is Bidirectional Streaming where a single gRPC call establishes a stream where both the client and the server can send a stream of messages to each other. The streamed messages are delivered in the order they were sent.
Protocol

The gRPC protocol specifies the abstract requirements for communication between clients and servers. A concrete embedding over HTTP/2 completes the picture by fleshing out the details of each of the required operations.
Abstract gRPC protocol

A gRPC RPC comprises of a bidirectional stream of messages, initiated by the client. In the client-to-server direction, this stream begins with a mandatory Call Header, followed by optional Initial-Metadata, followed by zero or more Payload Messages. The server-to-client direction contains an optional Initial-Metadata, followed by zero or more Payload Messages terminated with a mandatory Status and optional Status-Metadata (a.k.a.,Trailing-Metadata).

### Implementation over HTTP/2

The abstract protocol defined above is implemented over HTTP/2. gRPC bidirectional streams are mapped to HTTP/2 streams. The contents of Call Header and Initial Metadata are sent as HTTP/2 headers and subject to HPACK compression. Payload Messages are serialized into a byte stream of length prefixed gRPC frames which are then fragmented into HTTP/2 frames at the sender and reassembled at the receiver. Status and Trailing-Metadata are sent as HTTP/2 trailing headers (a.k.a., trailers).

### Flow Control

gRPC inherits the flow control mechanisms in HTTP/2 and uses them to enable fine-grained control of the amount of memory used for buffering in-flight messages.

## Rest Vs GRPC

The transport layer works using HTTP/2 on top of TCP/IP. It allows for lower latency (faster) connections that can take advantage of a single connection from client to server (which makes more efficient use of connection and can result in more efficient use of server resources.

HTTP/2 also supports bidirectional connectivity and asynchronous connectivity. So it is possible for the server to efficiently make contact with client to send messages (async response/notifications, etc..)

While, both REST and gRPC can generate client/server stubs (using something like swagger for REST), REST has a limited set of primary 'function' calls (or verbs):

     * GET     --> Read
     * PUT     --> Update/Replace
     * POST    --> Create/Insert
     * DELETE  --> Delete

whereas gRPC you can define any kind of function calls including synchronous/asynchronous, uni-direction/bidirectional(streams), etc..

Using gRPC the client makes a call to a local method. To the programmer, it looks like you're making a local call, but the underlying layer (the auto-generated client stub) sends the call to the server. To the server it looks like it's method was called locally.

gRPC takes care of all the underlying plumbing and simplifies the programming paradigm. However, to some dedicated REST purists, this may seem like an over-complication

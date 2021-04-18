package dev.timatifey.harmony.mappers

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: Message
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                Status.Success,
                data,
                Message()
            )
        }

        fun <T> error(msg: String? = null, data: T? = null): Resource<T> {
            return Resource(
                Status.Error,
                data,
                Message(message = msg)
            )
        }

        fun <T> error(msgId: Int? = null, data: T? = null): Resource<T> {
            return Resource(
                Status.Error,
                data,
                Message(messageId = msgId)
            )
        }
    }
}

data class Message(
    val message: String? = null,
    val messageId: Int? = null,
)

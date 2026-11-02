package sa.factory.app1290102.model

import kotlinx.serialization.Serializable

@Serializable
data class AuditWorkMilestone(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class AcademicManuscriptAudit(
    val requestId: String,
    val items: List<AuditWorkMilestone>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: AcademicManuscriptAuditStatus = AcademicManuscriptAuditStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class AcademicManuscriptAuditStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}

package fe.linksheet.module.database.entity.resolver

import androidx.room.Entity
import fe.linksheet.module.redactor.*
import fe.stringbuilder.util.*

@Entity(tableName = "amp2html_mapping", primaryKeys = ["ampUrl", "canonicalUrl"])
data class Amp2HtmlMapping(
    val ampUrl: String,
    val canonicalUrl: String
) : ResolverEntity<Amp2HtmlMapping>(), Redactable<Amp2HtmlMapping> {

    override fun urlResolved() = canonicalUrl

    override fun buildString(builder: ProtectedStringBuilder) {
        builder.wrapped(Bracket.Curly) {
            separated(Separator.Comma) {
                item { sensitive("ampUrl", StringUrl(ampUrl)) }
                item { sensitive("canonicalUrl", StringUrl(canonicalUrl)) }
            }
        }
    }

    override fun process(builder: StringBuilder, redactor: Redactor): StringBuilder {
        return builder.curlyWrapped {
//            commaSeparated {
//                item {
//                    redactor.process(builder, ampUrl, HashProcessor.UrlProcessor, "ampUrl=")
//                }
//                item {
//                    redactor.process(builder, canonicalUrl, HashProcessor.UrlProcessor, "canonicalUrl=")
//                }
//            }
        }
    }
}

package hirez.statuspage.json

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class StatusData(
			override val page: StatusPage,
			override val status: StatusData
) : IStatusPage, IStatus
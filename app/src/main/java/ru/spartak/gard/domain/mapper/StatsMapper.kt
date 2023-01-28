package ru.spartak.gard.domain.mapper

import ru.spartak.gard.data.network.dto.Data
import ru.spartak.gard.data.network.dto.Info
import ru.spartak.gard.data.network.dto.PerInput
import ru.spartak.gard.data.network.dto.Stats
import ru.spartak.gard.domain.model.DataModel
import ru.spartak.gard.domain.model.InfoModel
import ru.spartak.gard.domain.model.PerInputModel
import ru.spartak.gard.domain.model.StatsModel
import ru.spartak.gard.utils.Mapper

class StatsMapper() : Mapper<DataModel, Data> {
    override fun toDomain(t: Data): DataModel {
        return DataModel(t.name, t.globalStats.toDomain(), t.per_input.toDomain())
    }

    override fun fromDomain(domain: DataModel): Data {
        return Data(domain.name, domain.globalStats.fromDomain(), domain.per_input.fromDomain())
    }
}

fun Info.toDomain() = InfoModel(
    this.placetop1,
    this.placetop3,
    this.placetop5,
    this.placetop10,
    this.placetop12,
    this.placetop25,
    this.kd,
    this.kills,
    this.winrate,
    this.matchesplayed,
    this.minutesplayed,
    this.score,
)

fun Stats.toDomain() = StatsModel(this.solo.toDomain(), this.duo.toDomain(), this.squad.toDomain())

fun PerInput.toDomain() =
    PerInputModel(this.keyboardmouse.toDomain(), this.gamepad.toDomain(), this.touch.toDomain())


fun InfoModel.fromDomain() = Info(
    this.placetop1,
    this.placetop3,
    this.placetop5,
    this.placetop10,
    this.placetop12,
    this.placetop25,
    this.kd,
    this.kills,
    this.winrate,
    this.matchesplayed,
    this.minutesplayed,
    this.score,
)

fun StatsModel.fromDomain() =
    Stats(this.solo.fromDomain(), this.duo.fromDomain(), this.squad.fromDomain())

fun PerInputModel.fromDomain() =
    PerInput(this.keyboardmouse.fromDomain(), this.gamepad.fromDomain(), this.touch.fromDomain())
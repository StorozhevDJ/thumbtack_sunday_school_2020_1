package net.thumbtack.school.hospital.database.mappers;

import java.time.LocalDate;
import java.util.List;

import net.thumbtack.school.hospital.database.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface DayScheduleMapper {

	@Insert({"<script>",
			"INSERT INTO schedule (`doctorId`, `date`) VALUES",
			"<foreach item='item' collection='list' separator=','>",
			"( #{item.doctor.id}, #{item.date})",
			"</foreach>",
			"</script>"})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(@Param("list") List<DaySchedule> daySchedule);

	@Select("<script>" +
			"SELECT schedule.id, doctorId, date, room, speciality.name "
			+ "FROM `schedule` "
			+ "JOIN doctor ON doctor.id = doctorId "
			+ "JOIN room ON room.id = doctor.roomId "
			+ "JOIN speciality ON speciality.id = specialityId " +
			"<where> " +
			"<if test='doctorId > 0'> doctor.id = #{doctorId} </if> " +
			"<if test='speciality != null'> AND speciality.name = #{speciality} </if> " +
			"<if test='dateStart != null'> AND date >= #{dateStart} </if> " +
			"<if test='dateEnd != null'> AND #{dateEnd} > date </if> " +
			"</where> " +
			"ORDER BY date ASC " +
			"</script>")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "doctor", column = "doctorId",
					javaType = Doctor.class,
					one = @One(select = "net.thumbtack.school.hospital.database.mappers.DoctorMapper.getByDoctorId",
							fetchType = FetchType.LAZY)),
			@Result(property = "ticketSchedule", column = "id",
					javaType = List.class,
					one = @One(select = "net.thumbtack.school.hospital.database.mappers.TicketScheduleMapper.getByScheduleId",
							fetchType = FetchType.EAGER))
	})
	List<DaySchedule> getDaySchedule(@Param("doctorId") int doctorId, @Param("speciality") String speciality, @Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);

	@Select("SELECT ticket, date, timeStart, timeEnd, doctorId, schedule.id, type " +
			"FROM `schedule` " +
			"JOIN ticket_schedule ON schedule.id = scheduleId " +
			"WHERE patientId = #{patientId} ")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "doctor", column = "doctorId",
					javaType = Doctor.class,
					one = @One(select = "net.thumbtack.school.hospital.database.mappers.DoctorMapper.getByDoctorId",
							fetchType = FetchType.LAZY)),
			@Result(property = "ticketSchedule", column = "{scheduleId=id,type=type,timeStart=timeStart,timeEnd=timeEnd}",
					javaType = List.class,
					one = @One(select = "net.thumbtack.school.hospital.database.mappers.TicketScheduleMapper.getTicketSchedule",
							fetchType = FetchType.LAZY))
	})
	List<DaySchedule> getTicketListByPatientId(int patientId);

	// Used in delete doctor
	@Delete("DELETE FROM schedule WHERE doctorId = #{doctorId} AND date >= #{date};")
	void deleteScheduleByDoctorIdFromDate(@Param("doctorId") int doctorId, @Param("date") LocalDate date);

	// Used in update schedule
	@Delete("DELETE ticket_schedule.*, schedule.* " +
			"FROM ticket_schedule " +
			"JOIN schedule ON scheduleId = schedule.id " +
			"WHERE doctorId = #{doctorId} " +
			"AND date >= #{dateStart} AND date < #{dateEnd} " +
			"AND type='FREE';")
	void deleteFreeScheduleByDoctorIdByDate(@Param("doctorId") int doctorId, @Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);
}

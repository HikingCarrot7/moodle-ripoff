import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { useRouter } from 'next/router';

const renderEventContent = (eventInfo) => {
  return (
    <div className="cursor-pointer">
      <b>{eventInfo.timeText}</b>
      <br />
      <i>{eventInfo.event.extendedProps.course}</i>
      <br />
      <i>{eventInfo.event.title}</i>
    </div>
  );
};

export const Calendar = ({ title, assignments }) => {
  const router = useRouter();
  const events = assignments.map((assignment) => {
    return {
      id: assignment.id,
      course: assignment.course.name,
      title: assignment.title,
      date: assignment.dueDate,
    };
  });

  const goToAssignment = (assignmentId) => {
    router.push(`/assignment/${assignmentId}`);
  };

  return (
    <>
      <h2>{title}</h2>
      <FullCalendar
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        eventClick={({ event }) => goToAssignment(event.id)}
        selectMirror={true}
        selectable={true}
        eventContent={renderEventContent}
        events={events}
      />
    </>
  );
};

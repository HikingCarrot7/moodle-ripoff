import { AssignmentCard } from '@/components/assignments/AssignmentCard';

export const AssignmentList = ({ assignments }) => {
  return (
    <div className="assignment-list">
      {assignments.map((assignment, index) => (
        <AssignmentCard key={index} assignment={assignment} />
      ))}
    </div>
  );
};

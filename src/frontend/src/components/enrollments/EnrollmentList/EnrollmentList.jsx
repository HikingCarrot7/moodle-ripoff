import { EnrollmentCard } from '@/components/enrollments/EnrollmentCard';

export const EnrollmentList = ({ enrollments }) => {
  return (
    <div>
      <div>Participants</div>
      {enrollments.map((enrollment, index) => (
        <EnrollmentCard key={index} enrollment={enrollment} />
      ))}
    </div>
  );
};

export const EnrollmentCard = ({ enrollment }) => {
  const { createdAt, student } = enrollment;

  return (
    <div>
      <div>{createdAt}</div>
      <div>{student.name}</div>
      <div>{student.email}</div>
    </div>
  );
};

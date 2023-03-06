export const EnrollmentCard = ({ enrollment }) => {
  const { createdAt, student } = enrollment;

  return (
    <div className="flex justify-content-around">
      <div>{student.name}</div>
      <div>{student.email}</div>
      <div>{createdAt}</div>
    </div>
  );
};

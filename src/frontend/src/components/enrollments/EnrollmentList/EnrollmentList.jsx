import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';

export const EnrollmentList = ({ enrollments }) => {
  const students = enrollments.map((enrollment) => {
    return {
      name: enrollment.student.name,
      email: enrollment.student.email,
      enrolledAt: enrollment.createdAt,
    };
  });

  return (
    <div>
      <h2 className="text-4xl">Participants</h2>
      <DataTable value={students} size="small" tableStyle={{ width: '100%' }}>
        <Column field="name" header="Name"></Column>
        <Column field="email" header="Email"></Column>
        <Column field="enrolledAt" header="Enrollment At"></Column>
      </DataTable>
    </div>
  );
};

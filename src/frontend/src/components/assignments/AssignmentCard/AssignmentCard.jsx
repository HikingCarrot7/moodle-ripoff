import { Button } from 'primereact/button';
import { useRouter } from 'next/router';
import Link from 'next/link';

export const AssignmentCard = ({ assignment }) => {
  const router = useRouter();
  const { title, description, dueDate, completed } = assignment;

  const goToAssignment = () => {
    router.push(`/assignments/${assignment.id}`);
  };

  return (
    <div className="flex justify-content-start">
      <h3>{title}</h3>
      <p>{description}</p>
      <p>{dueDate}</p>
      <p>{completed}</p>
      <Button
        onClick={goToAssignment}
        label="View assignment"
        icon="pi pi-check"
      />
      <Link href={`/assignments/update?assignmentId=${assignment.id}`}>
        Update assignment
      </Link>
    </div>
  );
};

import { useRouter } from 'next/router';
import { Button } from 'primereact/button';
import { Badge } from 'primereact/badge';

export const AssignmentCard = ({ assignment }) => {
  const router = useRouter();
  const { title, description, dueDate } = assignment;

  const goToAssignment = () => {
    router.push(`/assignments/${assignment.id}`);
  };

  return (
    <div className="flex justify-content-start w-full">
      <div className="col-12 md:col-6 lg:col-3 w-full">
        <div className="surface-0 shadow-2 p-3 border-1 border-50 border-round">
          <div className="flex justify-content-between mb-3">
            <div>
              <span className="block text-900 font-bold mb-3 text-5xl">
                {title}
              </span>
              <div className="text-700 font-medium text-xl">{description}</div>
            </div>
            <div
              className="flex align-items-center justify-content-center bg-blue-100 border-round"
              style={{ width: '2.5rem', height: '2.5rem' }}>
              <i className="pi pi-file text-blue-500 text-xl"></i>
            </div>
          </div>
          <p>
            Due date: <Badge severity="warning" value={dueDate} />
          </p>
          <Button
            onClick={goToAssignment}
            label="View assignment"
            icon="pi pi-arrow-right"
          />
        </div>
      </div>
    </div>
  );
};
